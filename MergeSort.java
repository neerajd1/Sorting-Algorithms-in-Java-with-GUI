
package org.neeraj.algorithms.sorting;


public class MergeSort implements Sorter
{
	static Comparable [] arr;
    public void sort(Comparable[] array)
    {
    	this.arr=array;
    	mergeSort(0, arr.length-1);
    	array=this.arr;
    }

    private void mergeSort( int p, int r)
    {
		if (p < r) {
		    int q = (p + r) / 2;
		    mergeSort( p, q);
		    mergeSort( q+1, r);
		    merge(p, q, r);
		}
    }
    private void merge( int p, int q, int r)
    {
	int n1 = q - p + 1;
	int n2 = r - q;
	Comparable[] left = new Comparable[n1 + 1];
	Comparable[] right = new Comparable[n2 + 1];

	for (int i = 0; i < n1; i++)
	    left[i] = arr[p + i];

	for (int j = 0; j < n2; j++)
	    right[j] = arr[q + j + 1];

	left[n1] = null;	// null indicates infinity
	right[n2] = null;

	for (int i = 0, j = 0, k = p; k <= r; k++) {
	    if (compare(left[i], right[j]) <= 0)
		arr[k] = left[i++];
	    else
		arr[k] = right[j++];
	}
    }

    private int compare(Comparable x, Comparable y)
    {
	if (x == null) {
	    if (y == null)
		return 0;	   // both x and y are infinity
	    else
		return 1;	   // x is infinity, y is not
	}
	else if (y == null)
	    return -1;		   // y is infinity, x is not
	else
	    return x.compareTo(y); // neither x nor y is infinity
    }
}

