package industryProjects;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSort {
	public static void main(String[] args) {
		final int SIZE = 7000000;
		Integer[] list1 = new Integer[SIZE];
		Integer[] list2 = new Integer[SIZE];

		for (int i = 0; i < list1.length; i++)
			list1[i] = list2[i] = (int)(Math.random() * 1000000);

		long startTime = System.currentTimeMillis();
		parallelMergeSort(list1); // Invoke parallel merge sort
		long endTime = System.currentTimeMillis();
		System.out.println("\nParallel time with "
			+ Runtime.getRuntime().availableProcessors() + 
			" processors is " + (endTime - startTime) + " milliseconds");

		startTime = System.currentTimeMillis();
		MergeSort.mergeSort(list2); // MergeSort is in Listing 24.5
		endTime = System.currentTimeMillis();
		System.out.println("\nSequential time is " + 
			(endTime - startTime) + " milliseconds");
	}
	
	public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
		
		new SortTask<E>(list).compute();
	}

		  
	public static<E extends Comparable<E>> E[]
	    merge(E[] list1, E[] list2, E[] temp) {
	    
	    int current1 = 0; // Index in list1
	    int current2 = 0; // Index in list2
	    int current3 = 0; // Index in temp

	    while (current1 < list1.length && current2 < list2.length) {
	      if (list1[current1].compareTo(list2[current2]) < 0) {
	        temp[current3++] = list1[current1++];
	      } else {
	        temp[current3++] = list2[current2++];
	      }
	    }

	    while (current1 < list1.length) {
	      temp[current3++] = list1[current1++];
	    }

	    while (current2 < list2.length) {
	      temp[current3++] = list2[current2++];
	    }

	    return temp;  
	}
	
	

	/*public static void parallelMergeSort(Integer[] list) {
		SortTask mainTask = new SortTask(list);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(mainTask);
	}*/

	private  static class SortTask<E extends Comparable<E>> extends RecursiveAction {
		
		private final int THRESHOLD = 500;
		private E[] list;

		SortTask(E[] list) {
			this.list = list;
		}

		@Override
		protected void compute() {
			if (list.length < THRESHOLD) {
				java.util.Arrays.sort(list);
			} else {
				// Obtain the first half
				E[] firstHalf = (E[])new Comparable[list.length / 2];
				System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

				// Obtain the second half
				int secondHalfLength = list.length - list.length / 2;
				E[] secondHalf = (E[])new Comparable[secondHalfLength];
				System.arraycopy(list, list.length / 2, 
					secondHalf, 0, secondHalfLength);

				// Recursively sort the two halves
				invokeAll(new SortTask(firstHalf), 
					new SortTask(secondHalf));

				// Merge firstHalf with secondHalf into list
				MergeSort.merge(firstHalf, secondHalf, list);
			}
		}
	}
}