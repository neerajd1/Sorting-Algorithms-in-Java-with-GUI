package org.neeraj.algorithms.sorting;
public class IntegerValue implements NonNegativeInteger
{
    // An integer-valued key that should be nonnegative.
    private int key;

    // Initializes the key.
    public IntegerValue(int key)
    {
	if (key < 0)
	    throw new NegativeIntegerException();
	else
	    this.key = key;
    }


    // Sets the key.
    public void setKey(int key)
    {
	if (key < 0)
	    throw new NegativeIntegerException();
	else
	    this.key = key;
    }

    // Returns the key.
    public int getKey()
    {
	return key;
    }

    // Compares this IntegerValue to x.  Returns a negative integer if
    // this IntegerValue is less; 0 if the objects are equal; a
    // positive integer if this IntegerValue is greater.
    public int compareTo(Object x)
    {
	IntegerValue other = (IntegerValue) x;
	if (key < other.key)
	    return -1;
	else if (key == other.key)
	    return 0;
	else 
	    return 1;
    }

    // Returns the String representation of this IntegerValue object.
    public String toString()
    {
	return Integer.toString(key);
    }
}
