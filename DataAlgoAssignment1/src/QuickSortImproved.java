/*
 * The Quick Sort algorithm came from the professor
 * Thais Campanac-Climent
 * Notes: Tried to improve the algorithm by making a flip when it is less sorted but it didn't make the algorithm 
 * faster but instead made it slower
 */
public class QuickSortImproved {

	//to calculate the number of movements
	public int mov = 0;
	//to calculate the number of comparisons
	public int comp = 0;
	//to see if the algorithm is reversed or not
	double typeOfList = 0;
	
	public QuickSortImproved() {
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
		
		typeOfListNumber(list, list.length);
		System.out.println(typeOfList);
		if(typeOfList < .5) {
			flipList(list, list.length);
		}
	}
	
	public void quickSort(int[] list) {
		quickSort(list, 0, list.length-1);
	}
	
	private void typeOfListNumber(int[] arr, int size) {
		// TODO Auto-generated method stub
		for(int x = 0; x < size - 1; x++) {
			if(arr[x] < arr[x+1]) {
				typeOfList++;
			}
		}
		typeOfList = typeOfList / size;
	}
	
	private void flipList(int[] arr, int size) {
		// TODO Auto-generated method stub
		for(int x = 0; x< (size/2); x++) {
			int temp = arr[x];
			arr[x] = arr[size - x - 1];
			arr[size - x - 1] = temp;
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
