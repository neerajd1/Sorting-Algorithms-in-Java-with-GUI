package org.neeraj.algorithms.sorting;

public class ShellSort implements Sorter {

	@Override
	public void sort(Comparable[] array) {
		// TODO Auto-generated method stub
		int n = array.length;
		
		for (int gap = n/2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < n; i += 1)
            {
                Comparable temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap].compareTo(temp) > 0; j -= gap) 
                    array[j] = array[j - gap];
                array[j] = temp;
            }
        }
		
	}

}
