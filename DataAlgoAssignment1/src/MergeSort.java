/*
 * Professor has given the Merge Sort algorithm
 * Thais Campanac-Climent added the probes and get() functions
 */ 
public class MergeSort {
	
	//to calculate the number of movements
	public int mov = 0;
	//to calculate the number of comparisons
	public int comp = 0;

	public MergeSort() {
		// TODO Auto-generated constructor stub
	}
	//merge sort method at line 117 I have a stack overflow error
	public void merge(int[] list1, int[] list2, int[] temp) {
		int current1 = 0; //correlated to index of list1
		int current2 = 0; //correlated to index of list2
		int current3 = 0; //correlated to index of temp
		
		while(current1 < list1.length && current2 < list2.length) {
			comp++;
			
			comp++;
			if (list1[current1] < list2[current2]) {
				temp[current3++] = list1[current1++];
				mov++;
			}
			else {
				temp[current3++] = list2[current2++];
				mov++;
			}
		}
		
		while (current1 < list1.length) {
			comp++;
			temp[current3++] = list1[current1++];
			mov++;
		}
		
		while (current2 < list2.length) {
			comp++;
			temp[current3++] = list2[current2++];
			mov++;
		}
	}
	
	public void mergeSort(int[] list) {
		comp++;
		if(list.length > 1) {
			int[] firstHalf = new int[list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
			mergeSort(firstHalf);
			
			
			int secondHalfLength = list.length - list.length / 2;
			int[] secondHalf = new int[secondHalfLength];
			System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
			mergeSort(secondHalf);
			
			merge(firstHalf, secondHalf, list);
		}
	}
	
	//returning all probes to the other class
	public int getMov() {
		return mov;
	}
	public int getComp() {
		return comp;
	}
}
