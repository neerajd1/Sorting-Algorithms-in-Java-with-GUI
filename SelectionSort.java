package org.neeraj.algorithms.sorting;

public class SelectionSort implements Sorter {

	@Override
	public void sort(Comparable[] array) {
		// TODO Auto-generated method stub
		
		int n=array.length;
		 for (int i = 0; i < n-1; i++)
	        {
	            int min_idx = i;
	            for (int j = i+1; j < n; j++)
	                if (array[j].compareTo(array[min_idx]) < 0 )
	                    min_idx = j;
	            Comparable temp = array[min_idx];
	            array[min_idx] = array[i];
	            array[i] = temp;
	        }
	}
}
