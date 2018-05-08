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

// Value.java
// Simple class for a number, implementing Comparable and
// DoubleValued.
package org.neeraj.algorithms.sorting;

public class Value implements Comparable, DoubleValued
{
    private double theNumber;	// the number being stored

    // Constructor.  Stores a number.
    public Value(double n)
    {
	theNumber = n;
    }

    // Stores a number.
    public void setKey(double key)
    {
	theNumber = key;
    }

    // Returns the number stored.
    public double getKey()
    {
	return theNumber;
    }

    // Compares this Value to another, based on their stored numbers.
    // Returns a negative integer if this Value's number is less; 0 if
    // the numbers are equal; a positive integer if this Value's
    // number is greater.
    public int compareTo(Object e)
    {
    	double otherNumber = ((Value) e).theNumber;
    	if (theNumber < otherNumber)
	    return -1;
    	else if (theNumber == otherNumber)
	    return 0;
    	else
	    return 1;
    }

    // Returns the String representation of the stored number.
    public String toString()
    {
	return (new Double(theNumber)).toString();
    }
}
