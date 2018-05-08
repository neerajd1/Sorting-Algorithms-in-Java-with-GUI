package org.neeraj.algorithms.sorting;

public class GnomeSort implements Sorter {

	@Override
	public void sort(Comparable[] array) {
		// TODO Auto-generated method stub
		int n = array.length,index = 0;
		 
        while (index < n) {
            if (index == 0)
                index++;
            if (array[index].compareTo(array[index - 1]) >= 0)
                index++;
            else {
                Comparable temp = 0;
                temp = array[index];
                array[index] = array[index - 1];
                array[index - 1] = temp;
                index--;
            }
        }
	}

}
