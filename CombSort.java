package org.neeraj.algorithms.sorting;

public class CombSort  implements Sorter
{

	int getNextGap(int gap)
    {
        // Shrink gap by Shrink factor
        gap = (gap*10)/13;
        if (gap < 1)
            return 1;
        return gap;
    }
	@Override
	public void sort(Comparable[] array)
    {
		int n = array.length;
		int gap = n;
		 
        // Initialize swapped as true to make sure that
        // loop runs
        boolean swapped = true;
 
        // Keep running while gap is more than 1 and last
        // iteration caused a swap
        while (gap != 1 || swapped == true)
        {
            // Find next gap
            gap = getNextGap(gap);
 
            // Initialize swapped as false so that we can
            // check if swap happened or not
            swapped = false;
 
            // Compare all elements with current gap
            for (int i=0; i<n-gap; i++)
            {
                if (compare(array[i],array[i+gap])==1)
                {
                    // Swap arr[i] and arr[i+gap]
                    Comparable temp = array[i];
                    array[i] = array[i+gap];
                    array[i+gap] = temp;
                    // Set swapped
                    swapped = true;
                }
            }
        }        
    }
	 private int compare(Comparable x, Comparable y)
	    {
		
		    return x.compareTo(y);
	    }

}
