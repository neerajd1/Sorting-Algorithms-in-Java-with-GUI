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

package org.neeraj.algorithms.sorting;


public class InsertionSort implements Sorter
{
 
    public void sort(Comparable[] array)
    {
	int n = array.length;
	for (int j = 1; j < n; j++) {
	    Comparable k = array[j];
	    int i = j-1;
	    
	    while (i >= 0 && array[i].compareTo(k) > 0) {
		array[i+1] = array[i];
		i--;
	    }

	    array[i+1] = k;
	}
    }
}

