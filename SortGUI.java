/************************************************************************
 *
 * 1. This software is for the purpose of demonstrating one of many
 * ways to implement the algorithms in Introduction to Algorithms,
 * Second edition, by Thomas H. Cormen, Charles E. Leiserson, Ronald
 * L. Rivest, and Clifford Stein.  This software has been tested on a
 * limited set of test cases, but it has not been exhaustively tested.
 * It should not be used for mission-critical applications without
 * further testing.
 *
 * 2. McGraw-Hill licenses and authorizes you to use this software
 * only on a microcomputer located within your own facilities.
 *
 * 3. You will abide by the Copyright Law of the United Sates.
 *
 * 4. You may prepare a derivative version of this software provided
 * that your source code indicates that it based on this software and
 * also that you have made changes to it.
 *
 * 5. If you believe that you have found an error in this software,
 * please send email to clrs-java-bugs@mhhe.com.  If you have a
 * suggestion for an improvement, please send email to
 * clrs-java-suggestions@mhhe.com.
 *
 ***********************************************************************/
/*Credits to McGraw-Hill this is an derivative version
 * Included different sorting algorithms.
 * Issues fixed MergeSort
 * 
 * # Sorting-Algorithms-in-Java-with-GUI
Sorting algorithm implemented in java


Purpose:- To implement sorting algorithms.


Algorithms used:- Merge Sort, Bubble sort, insertion sort, selection sort, quick sort, heap sort, counting sort, comb sort, radix sort, cycle sort, gnome sort, pancake sort, shell sort.


References:- Introduction to Algorithms, Second edition [1] geeksforgeeks.org [2].

Credits:- McGraw-Hill software implementation. This is a derived version of McGraw Hill software as per McGraw-Hill licenses. 
 * 
 * */
package org.neeraj.algorithms.sorting;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.border.*;
import java.util.Random;

public class SortGUI extends JFrame
{
    protected JCheckBox integersOnly;
    protected JTextField randText;
    protected ListPanel listPanel;
    protected Algorithm algorithm = Algorithm.INSERT;
    
    protected static class Algorithm{
		final public String name;
		final public Class factory;
		
		private Algorithm(String name, Class factory) {
		    this.name = name;
		    this.factory = factory;
		}
	
		public String toString() {
		    return name;
		}
	
		public static final Algorithm INSERT = 
		    new Algorithm("InsertionSort", InsertionSort.class);
		public static final Algorithm HEAP = 
		    new Algorithm("HeapSort", Heapsort.class);
		public static final Algorithm QUICK = 
		    new Algorithm("Quicksort", Quicksort.class);
		public static final Algorithm COUNT = 
		    new Algorithm("CountingSort", CountingSort.class);
		public static final Algorithm RADIX = 
		    new Algorithm("RadixSort", RadixSort.class);
		public static final Algorithm BUBBLE = 
			    new Algorithm("BubbleSort", BubbleSort.class);
		public static final Algorithm COMB = 
			    new Algorithm("CombSort", CombSort.class);
		public static final Algorithm CYCLE = 
			    new Algorithm("CycleSort", CycleSort.class);
		public static final Algorithm MERGE = 
			    new Algorithm("MergeSort", MergeSort.class);
		public static final Algorithm SELECT = 
			    new Algorithm("SelectionSort", SelectionSort.class);
		public static final Algorithm GNOME = 
			    new Algorithm("GnomeSort", GnomeSort.class);
		public static final Algorithm SHELL = 
			    new Algorithm("ShellSort", ShellSort.class);
		public static final Algorithm PANCAKE = 
			    new Algorithm("PancakeSort", PancakeSort.class);
	
		public static final Algorithm[] SortAlgo = {BUBBLE, MERGE, 
				INSERT, SELECT , QUICK, HEAP, SHELL ,  
				RADIX , COUNT , CYCLE , COMB, GNOME , PANCAKE};
    }

    public SortGUI(String title)
    {
		super(title);
		makeContentPane();
    }

    protected void makeContentPane()
    {
		JPanel algPanel = makeAlgorithmPanel();
		JPanel randPanel = makeRandomPanel();
		JPanel csPanel = makeClearSortPanel();
		listPanel = new ListPanel(NumberFormat.getInstance());
	
		Container contentPane = getContentPane();
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		contentPane.setLayout(gb);
	
		gc.weightx = 0.5; gc.weighty = 0.5;
		gc.fill = GridBagConstraints.BOTH;
	
		gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 2;
		gb.setConstraints(algPanel, gc);
		contentPane.add(algPanel);
	
		gc.gridwidth = 1; gc.gridx = 2; gc.gridheight = 2;
		gb.setConstraints(listPanel, gc);
		contentPane.add(listPanel);
	
		gc.gridx = 0; gc.gridy = 1; gc.gridheight = 1;
		gb.setConstraints(randPanel, gc);
		contentPane.add(randPanel);
		
		gc.gridx = 1; gc.gridy = 1;
		gb.setConstraints(csPanel, gc);
		contentPane.add(csPanel);	
	}
	
	protected JPanel makeAlgorithmPanel() {
		JRadioButton[] algButtons;
		ButtonGroup group;
	
		JPanel panel = new JPanel();
		ActionListener algListener = new AlgorithmListener();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
		group = new ButtonGroup(); 
		algButtons = new JRadioButton[Algorithm.SortAlgo.length];
	
		for (int i = 0; i < Algorithm.SortAlgo.length; i++) {
		    algButtons[i] = new JRadioButton(Algorithm.SortAlgo[i].name);
		    algButtons[i].setActionCommand(Algorithm.SortAlgo[i].name);
		    algButtons[i].addActionListener(algListener);
		    group.add(algButtons[i]);
		    panel.add(algButtons[i]);
		}
	
		algButtons[0].setSelected(true);
	
		integersOnly = new JCheckBox("Restrict to Integers");
		integersOnly.addItemListener(new IntegerListener());
		panel.add(integersOnly);
		
		panel.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createEtchedBorder
				 (EtchedBorder.RAISED),
				 "Algorithm"));
		return panel;
	 }
	
	    protected JPanel makeRandomPanel() {
		JPanel panel = new JPanel();
		JPanel subpanel = new JPanel();
		subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel randLabel = new JLabel("Count");
		randText = new JTextField(3);	
		JButton randButton = new JButton("Generate");	
		randButton.addActionListener(new RandomListener());
		randText.addActionListener(new RandomListener());
		subpanel.add(randLabel);
		subpanel.add(randText);
		panel.add(subpanel);
		panel.add(randButton);
		panel.setBorder(BorderFactory.createTitledBorder
				    (BorderFactory.createEtchedBorder
				     (EtchedBorder.RAISED),
				     "Random Input"));
		return panel;
    }

    protected JPanel makeClearSortPanel()
    {
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

	JButton clear = new JButton("Clear");
	clear.addActionListener(new ClearListener());

	JButton sort = new JButton("Sort");
	sort.addActionListener(new SortListener());

	panel.add(clear);
	panel.add(sort);
	
	panel.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createEtchedBorder
			 (EtchedBorder.RAISED),
			 "Operations"));
	return panel;
    }

    protected class AlgorithmListener implements ActionListener
    {
	public void actionPerformed(ActionEvent ev)
	{
	    String name = ev.getActionCommand();
	    for (int i = 0; i < Algorithm.SortAlgo.length; i++)
		if (Algorithm.SortAlgo[i].name == name)
		    algorithm = Algorithm.SortAlgo[i];

	    if (algorithm == Algorithm.COUNT ||	algorithm == Algorithm.RADIX) {
			integersOnly.setSelected(true);
			integersOnly.setEnabled(false);
	    }
	    
	    else 
	    	integersOnly.setEnabled(true);
	}
    }
    protected class IntegerListener implements ItemListener
    {
	public void itemStateChanged(ItemEvent ev)
	{
	    if (ev.getStateChange() == ItemEvent.SELECTED) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setParseIntegerOnly(true);
		listPanel.setFormat(nf);
	    }	    
	    else if (ev.getStateChange() == ItemEvent.DESELECTED) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setParseIntegerOnly(false);
		listPanel.setFormat(nf);
	    }
	}
    }

    protected class RandomListener implements ActionListener
    {
		protected Random rand;
	
		public RandomListener() {
		    rand = new Random();
		}
	
		public void actionPerformed(ActionEvent ev)	{
			    int count = Integer.parseInt(randText.getText());
			    if (integersOnly.isSelected()) {
				for (int i = 0; i < count; i++)
				    listPanel.addElement(new Integer(rand.nextInt(100000)));
			    }
			    else {
				for (int i = 0; i < count; i++)
				    listPanel.addElement(new Double(rand.nextDouble()));
			    }
			}
	    }
	
	    protected class ClearListener implements ActionListener {
			public void actionPerformed(ActionEvent ev)	{
			    listPanel.clear();
			}
    }

    protected class SortListener implements ActionListener {
		public void actionPerformed(ActionEvent ev)
		{
		    Object[] objects =  listPanel.toArray();
		    Number[] numbers = new Number[objects.length];
		    for(int i = 0; i < objects.length; i++)
		    	numbers[i] = (Number) objects[i];
		    if (algorithm == Algorithm.COUNT){
		    	CountingSort sorter = new CountingSort();
			NonNegativeInteger[] array = new NonNegativeInteger[numbers.length];
			for (int i = 0; i < numbers.length; i++)
			    array[i] = (NonNegativeInteger) new IntegerValue(numbers[i].intValue());
			sorter.sort(array);
			listPanel.clear();
			for(int i = 0; i < array.length; i++)
			    listPanel.addElement(new Integer(array[i].getKey()));
		    }
		    else if (algorithm == Algorithm.RADIX) {
				RadixSort sorter = new RadixSort();
				NonNegativeInteger[] array = 
				    new NonNegativeInteger[numbers.length];
				for (int i = 0; i < numbers.length; i++)
				    array[i] = (NonNegativeInteger) new IntegerValue(numbers[i].intValue());		
				sorter.sort(array);
				listPanel.clear();
				for (int i = 0; i < array.length; i++)
				    listPanel.addElement(new Integer(array[i].getKey()));
		    }
		    else {
				try {
					    Sorter sorter = (Sorter) algorithm.factory.newInstance();
			
					    Value[] array = new Value[numbers.length];
					    for (int i = 0; i < numbers.length; i++)
					    	array[i] = new Value(numbers[i].doubleValue());
					    sorter.sort(array);
					    listPanel.clear();
					    for (int i = 0; i < array.length; i++)
					    	listPanel.addElement(new Double(array[i].getKey()));
				}
				catch (InstantiationException ex) {
				    System.err.println("Couldn't Instantiate a sorter");
				}
				catch (IllegalAccessException ex) {
				    System.err.println("Couldn't Instantiate a sorter");
				}
		    }
		}
    }

    public static void main(String[] args)
    {
		JFrame gui = new SortGUI("Sorting Algorithms");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.pack();
		gui.setVisible(true);
    }
}
