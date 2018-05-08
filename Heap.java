package org.neeraj.algorithms.sorting;

abstract public class Heap 
{
    protected Comparable[] array;
    protected int heapSize;
    abstract public void heapify(int i);
    public Heap()
    {
		array = null;
		heapSize = 0;
    }
    public Heap(Comparable[] array)
    {
		this.array = array;
		heapSize = array.length;
		buildHeap();
    }
    protected void exchange(int i, int j)
    {
		Comparable c = array[i];
		array[i] = array[j];
		array[j] = c;
    }
    public static final int parent(int i)
    {
    	return (i-1) / 2;
    }

    public static final int left(int i)
    {
    	return 2 * i + 1;
    }

    public static final int right(int i)
    {
    	return 2 * i + 2;
    }

    public final boolean isEmpty()
    {
    	return heapSize < 1;
    }
    public Comparable head()
    {
    	return array[0];
    }

    public void buildHeap()
    {
		// Heapify from the bottom up. 
		for (int i = array.length/2; i >= 0; i--)
		    heapify(i);
    }

    public Sorter makeSorter()
    {
    	return new Heapsort();
    }

    public class Heapsort implements Sorter
    {
	
		public void sort(Comparable[] arrayToSort)
		{
		    array = arrayToSort;
		    heapSize = array.length;
		    buildHeap();
		    for (int i = array.length-1; i >= 1; i--) {
			exchange(0, i);
			heapSize--;
			heapify(0);
		    }
		}
    }

    protected static class Handle implements Comparable
    {
		protected int index;
		protected DynamicSetElement info;
		protected Handle(int index, DynamicSetElement info)
		{
		    this.index = index;
		    this.info = info;
		}
		public int compareTo(Object e)
		{
		    return info.compareTo(((Handle) e).info);
		}
    }
}
