package org.neeraj.algorithms.sorting;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.border.*;

public class ListPanel extends JPanel
{
    protected String title = "List Panel";
    protected int numcols = 10;
    protected Format format;
    protected ValidatedTextField text;
    protected JList list;
    protected DefaultListModel listModel;
    protected JButton addButton;
    protected JButton removeButton;
    protected JButton upButton;
    protected JButton downButton;

    // Makes a new list panel with the title title and a the formatter
    // format.
    public ListPanel(String title, Format format)
    {
	this.format = format;
	this.title = title;
	makeContentPane();
    }

    // Uses the default title and use format as a formatter.
    public ListPanel(Format format)
    {
	this.format = format;
	makeContentPane();
    }

    protected void makeContentPane()
    {
	text = new ValidatedTextField(numcols, format);
	listModel = new DefaultListModel();

	list = new JList(listModel);
	list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	JScrollPane scroller = new JScrollPane(list);

	addButton = new JButton("Add");
	removeButton = new JButton("Remove");
	upButton = new JButton("Up");
	downButton = new JButton("Down");

	removeButton.setEnabled(false);
	downButton.setEnabled(false);
	upButton.setEnabled(false);

	text.addActionListener(new AddItemListener());
	addButton.addActionListener(new AddItemListener());
	removeButton.addActionListener(new RemoveItemListener());
	upButton.addActionListener(new MoveUpListener());
	downButton.addActionListener(new MoveDownListener());
	list.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent ev)
		{
		    if(list.getSelectedIndex() > -1)
			enableBottomButtons(true);
		    else
			enableBottomButtons(false);
		}
	    });

	JPanel topPane = new JPanel();
	topPane.setLayout(new BoxLayout(topPane, BoxLayout.X_AXIS));
	JPanel bottomPane = new JPanel();
	bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.X_AXIS));

	topPane.add(text);
	topPane.add(Box.createHorizontalGlue());
	topPane.add(addButton);

	bottomPane.add(upButton);
	bottomPane.add(downButton);
	bottomPane.add(Box.createHorizontalGlue());
	bottomPane.add(removeButton);

	setLayout(new BorderLayout());
	add(topPane, BorderLayout.NORTH);
	add(scroller, BorderLayout.CENTER);
	add(bottomPane, BorderLayout.SOUTH);
	setBorder(BorderFactory.createTitledBorder
		  (BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
		   title));
    }

    // Enables/disables the up, down and remove buttons.
    private void enableBottomButtons(boolean b)
    {
	upButton.setEnabled(b);
	downButton.setEnabled(b);
	removeButton.setEnabled(b);
    }

    // Moves the selected items down when the down button is pushed.
    protected class MoveDownListener implements ActionListener
    {
	public void actionPerformed(ActionEvent ev)
	{
	    int min = list.getMinSelectionIndex();
	    int max = list.getMaxSelectionIndex();

	    if (max < listModel.getSize() - 1) {
		Object temp = listModel.remove(max + 1);
		listModel.add(min, temp);
		list.setSelectionInterval(min + 1, max + 1);
	    }
	}
    }	

    // Moves the selection up a space when the up button is pressed.
    protected class MoveUpListener implements ActionListener
    {
	public void actionPerformed(ActionEvent ev)
	{
	    int min = list.getMinSelectionIndex();
	    int max = list.getMaxSelectionIndex();

	    if (min > 0) {
		Object temp = listModel.remove(min - 1);
		listModel.add(max, temp);
		list.setSelectionInterval(min - 1, max - 1);
	    }
	}
    }

    // Adds the item in the text field. 
    protected class AddItemListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    if (text.getText().length() > 0) {
		int index = list.getMaxSelectionIndex();
		if (index < 0)
		    index = listModel.getSize();
		else
		    index++;

		try {
		    listModel.add(index, format.parseObject(text.getText()));
		}
		catch(ParseException ex) {
		    Toolkit.getDefaultToolkit().beep();
		}

		list.ensureIndexIsVisible(index);
		text.selectAll();
		text.requestFocus();
	    }
	}
    }

    // Removes the selected item.
    protected class RemoveItemListener implements ActionListener
    {
	public void actionPerformed(ActionEvent e)
	{
	    int min = list.getMinSelectionIndex();
	    int max = list.getMaxSelectionIndex();
	    
	    listModel.removeRange(min,max);

	    if (listModel.getSize() < 1) {
		enableBottomButtons(false);
	    }
	    else {
		if (min == listModel.getSize())
		    min--;
		list.setSelectedIndex(min);
		list.ensureIndexIsVisible(min);
	    }
	}
    }

    // Returns the contents of the list as an Object array.
    public Object[] toArray()
    {
	return listModel.toArray();
    }

    // Appends the array to the contents of the list.
    public void addArray(Object[] array)
    {
	for (int i = 0; i < array.length; i++)
	    listModel.addElement(array[i]);
    }

    // Appends x to the contents of the list. 
    public void addElement(Object x)
    {
	listModel.addElement(x);
    }

    // Empties the list.
    public void clear()
    {
	listModel.clear();
    }

    // Changes the formatter.
    public void setFormat(Format format)
    {
	this.format = format;
	text.setFormat(format);
	listModel.clear();
	enableBottomButtons(false);
    }
}
