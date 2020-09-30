/*
 * The Radix Sort algorithm came from the professor
 * Thais Campanac-Climent
 * Notes: Tried to improve the algorithm by making a flip when it is less sorted but it didn't make the algorithm 
 * faster but instead made it slower
 */
import java.util.Arrays;

public class RadixSortImproved {
	
	//to calculate the number of movements
	int mov = 0;
	//to calculate the number of comparisons
	int comp = 0;
	//to see if the algorithm is reversed or not
	double typeOfList = 0;

	public RadixSortImproved() {
		// TODO Auto-generated constructor stub
	}
		//radix sort method
		int getMax(int arr[], int n) {
			int mx = arr[0];
			
			for(int i = 1; i < n; i++) {
				
				comp++;
				if(arr[i] > mx) {
					mx = arr[i];
				}
			}
			return mx;
		}
		
		void countSort(int[] arr, int n, int exp) {
			int[] output = new int[n];
			int i;
			int[] count = new int[10];
			Arrays.fill(count, 0);
			
			for(i = 0; i < n; i++) {
				count[(arr[i] / exp) % 10]++;
			}
			
			for(i = 1; i < 10; i++) {
				count[i] += count[i - 1];
			}
			
			for(i = n - 1; i >= 0; i--) {
				output[count[(arr[i]/exp) % 10] - 1] = arr[i];
				count[(arr[i]/exp) % 10]--;
			}
			
			for(i = 0; i < n; i++) {
				arr[i] = output[i];
				mov++;
			}
		}
		
		public void radixSort(int[] arr, int n) {
			int m = getMax(arr, n);
			
			for (int exp = 1;m/exp > 0; exp *= 10) {
				countSort(arr, n, exp);
				typeOfListNumber(arr, n);
				System.out.println(typeOfList);
				if(typeOfList < .5) {
					flipList(arr, n);
				}
			}
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

