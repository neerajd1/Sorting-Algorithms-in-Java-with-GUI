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

// ValidatedTextField.java
// Class for a text field that is action validated.
package org.neeraj.algorithms.sorting;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class ValidatedTextField extends JTextField
{
    private Format formatter;

    protected class ValidatingDocument extends PlainDocument
    {
	// Make sure that the resulting string complies with the formatter
	// before allowing an insertion.
	public void insertString(int offset, String input, AttributeSet attr)
	    throws BadLocationException
	{
	    String current = getText(0, getLength());
	    String left = current.substring(0, offset);
	    String right = current.substring(offset, current.length());
	    String result = left + input + right;

	    try {
		formatter.parseObject(result);
		super.insertString(offset, input, attr);
	    }
	    catch (ParseException e) {
		Toolkit.getDefaultToolkit().beep();
		System.err.println("Invalid input: " + input);
	    }
	}
    }

    // Makes a ValidatedTextField cols wide with formatter as its
    // formatter.
    public ValidatedTextField(int cols, Format formatter)
    {
	super(cols);
	this.formatter = formatter;
    }
    
    // Sets the value of the field to the argument.
    public void setValue(Object obj)
    {
	setText(formatter.format(obj));
    }

    // Parses the text in the field and return an Object.
    public Object getValue()
    {
	try {
	    return formatter.parseObject(getText());
	}
	catch(ParseException ex) {
	    return null;
	}
    }

    protected Document createDefaultModel() 
    {
	return new ValidatingDocument();
    }

    // Changes the formatter.
    public void setFormat(Format formatter)
    {
	this.formatter = formatter;
	setDocument(new ValidatingDocument());
    }
}
