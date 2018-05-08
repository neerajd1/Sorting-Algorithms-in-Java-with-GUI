package org.neeraj.algorithms.sorting;

public class CycleSort implements Sorter {

	@Override
	public void sort(Comparable[] array) {
		// TODO Auto-generated method stub
		int n = array.length;
		int writes = 0;
		 
        // traverse array elements and put it to on
        // the right place
        for (int cycle_start = 0; cycle_start <= n - 2; cycle_start++) {
            // initialize item as starting point
            Comparable item = array[cycle_start];
 
            // Find position where we put the item. We basically
            // count all smaller elements on right side of item.
            int pos = cycle_start;
            for (int i = cycle_start + 1; i < n; i++)
                if (array[i].compareTo(item)<0 )
                    pos++;
 
            // If item is already in correct position
            if (pos == cycle_start)
                continue;
 
            // ignore all duplicate elements
            while (item == array[pos])
                pos += 1;
 
            // put the item to it's right position
            if (pos != cycle_start) {
                Comparable temp = item;
                item = array[pos];
                array[pos] = temp;
                writes++;
            }
 
            // Rotate rest of the cycle
            while (pos != cycle_start) {
                pos = cycle_start;
 
                // Find position where we put the element
                for (int i = cycle_start + 1; i < n; i++)
                    if (array[i].compareTo(item)<0)
                        pos += 1;
 
                // ignore all duplicate elements
                while (array[pos].compareTo(item)==0)
                    pos += 1;
 
                // put the item to it's right position
                if (item != array[pos]) {
                    Comparable temp = item;
                    item = array[pos];
                    array[pos] = temp;
                    writes++;
                }
            }
        }
	}

}
