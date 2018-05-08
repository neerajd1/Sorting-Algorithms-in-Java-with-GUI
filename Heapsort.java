
package org.neeraj.algorithms.sorting;

public class Heapsort implements Sorter
{
    public void sort(Comparable[] array)
    {
		Heap heap = new MaxHeap();
		Sorter heapsorter = heap.makeSorter();
		heapsorter.sort(array);
    }
}
