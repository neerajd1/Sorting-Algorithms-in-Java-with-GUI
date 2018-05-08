package org.neeraj.algorithms.sorting;

public class PancakeSort implements Sorter {
	
	static void flip(Comparable arr[], int i)
    {
        int start = 0;
        Comparable temp;
        while (start < i)
        {
            temp = arr[start];
            arr[start] = arr[i];
            arr[i] = temp;
            start++;
            i--;
        }
    }
  
    static int findMax(Comparable arr[], int n)
    {
        int mi, i;
        for (mi = 0, i = 0; i < n; ++i)
            if (arr[i].compareTo(arr[mi]) > 0)
                mi = i;
        return mi;
    }

	@Override
	public void sort(Comparable[] array) {
		// TODO Auto-generated method stub
		int n= array.length;
		
		for (int curr_size = n; curr_size > 1; --curr_size)
        {
            int mi = findMax(array, curr_size);
 
            if (mi != curr_size-1)
            {
                flip(array, mi);
 
                flip(array, curr_size-1);
            }
        }
		
	}

}
