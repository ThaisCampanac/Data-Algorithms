/*
 * Professor has given the Quick Sort algorithm
 * Thais Campanac-Climent added the probes and get() functions
 */ 
public class QuickSort {
	
	//to calculate the number of movements
	public int mov = 0;
	//to calculate the number of comparisons
	public int comp = 0;

	public QuickSort() {
		// TODO Auto-generated constructor stub
	}
	//quick sort method
	public int partition(int[] list, int first, int last) {
		int pivot = list[(first+last)/2];
		int low = first + 1;
		int high = last;
		
		while(high > low) {
			comp++;
			
			while(low <= high && list[low] <= pivot) {
				comp++;
				low++;
			}
			
			while(low <= high && list[high] > pivot) {
				comp++;
				high--;
			}

			comp++;
			if(high >low) {
				int temp = list[high];
				list[high] = list[low];
				mov++;
				list[low] = temp;
				mov++;
			}
		}
		
		while (high > first && list[high] >= pivot) {
			comp++;
			high--;
			
		}

		comp++;
		if(pivot > list[high]) {
			list[first] = list[high];
			mov++;
			list[high]= pivot;
			mov++;
			return high;
		}
		else {
			return first;
		}
	}
	
	public void quickSort(int[] list, int first, int last) {
		comp++;
		if(last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}
	
	public void quickSort(int[] list) {
		quickSort(list, 0, list.length-1);
	}
	
	//returning all probes to the other class
	public int getMov() {
		return mov;
	}
	public int getComp() {
		return comp;
	}
}
