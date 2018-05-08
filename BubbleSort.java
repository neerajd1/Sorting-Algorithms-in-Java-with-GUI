package org.neeraj.algorithms.sorting;

public class BubbleSort implements Sorter{

	@Override
	public void sort(Comparable[] array)
    {
		int n = array.length;
		int i, j;
		Comparable temp;
		for(i=0;i<n;i++)
			System.out.println(array[i]);
			
        boolean swapped;
        for (i = 0; i < n - 1; i++) 
        {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) 
            {
                if (compare(array[j],array[j + 1])==1) 
                {
                    // swap arr[j] and arr[j+1]
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
 
            if (swapped == false)
                break;
        }        
    }
	 private int compare(Comparable x, Comparable y)
	    {
		
		    return x.compareTo(y);
	    }
}
