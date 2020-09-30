/*
 * InsertionSort Algorithm given by professor. 
 * The additional probes and get() functions were implemented by Thais Chloe Campanac-Climent.
 */ 
public class InsertionSort {
	
	//to calculate the number of movements that happen to the list
	public long mov = 0;
	//to calculate the number of comparisons
	public int comp = 0;
	

	public InsertionSort() {
		// TODO Auto-generated constructor stub
	}
	
	//insertion sort method
		public void insertionSort(int[] list) {
			for(int i = 1; i < list.length; i ++) {
				int currentElement = list[i];
				int k;

				comp++;
				for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
					int checkMov = list[k+1];
					list[k + 1] = list[k];

					//not a comparison that is a part of the algorithm
					if(checkMov != list[k+1])
						mov++;
				}
				
				int checkMov2 = list[k+1];
				
				list[k+1] = currentElement;
				if(checkMov2 != list[k+1])
					mov++;
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
