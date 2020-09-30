/*
 * Professor has given the Heap algorithm
 * Thais Campanac-Climent added the probes and get() functions
 */ 
public class SelectionSort {
	
	//to calculate the number of movements
	public long mov = 0;
	//to calculate the number of comparisons
	public int comp = 0;

	public SelectionSort() {
		// TODO Auto-generated constructor stub
	}
	
	//selection sort method
	public void selectionSort(int[] inputlist) {
		for(int i = 0; i < inputlist.length - 1; i++) {
			int currentMin = inputlist[i];
			int currentMinIndex = i;
			
			for(int j = i + 1; j < inputlist.length; j++) {
				comp++;
				if (currentMin > inputlist[j]) {
					currentMin = inputlist[j];
					currentMinIndex = j;
				}
			}
			
			comp++;
			if (currentMinIndex != i) {
				inputlist[currentMinIndex] = inputlist[i];
				mov++;
				inputlist[i] = currentMin;
				mov++;
			}
		}
	}
	
	//returning all probes to the other class
	public long getMov() {
		return mov;
	}
	public int getComp() {
		return comp;
	}
}
