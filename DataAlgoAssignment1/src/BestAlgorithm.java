/************************************
author: Thais Chloe Campanac-Climent
Project Name: Test Efficiency of Sorting Algorithms
Project Explained: Six sorting algorithms were given and it is up to the programmer to test the efficiency of these 
algorithms and making a GUI while making small improvements to the algorithms
Class Explained: This class is to select the best method to sort a given list
Version: 1.3
Date: 9/18/20
*************************************/
public class BestAlgorithm {
	
	String bestAlgo;
	String typeList;
	double typeOfList = 0;
	long durationInNano;
	long durationOfFlip;
	long mov;
	long comp;

	public BestAlgorithm() {
		// TODO Auto-generated constructor stub
	}
	
	public void findBestAlgo(int[] arr, int size) {
		typeOfList(arr, size);
		useBestSort(arr, size);
	}

	private void useBestSort(int[] arr, int size) {
		// TODO Auto-generated method stub
		if(bestAlgo.equals("Insertion Sort")) {
			InsertionSort insert = new InsertionSort();
			long startTime = System.nanoTime();
			insert.insertionSort(arr);
			long endTime = System.nanoTime();
			mov = insert.getMov();
			comp = insert.getComp();
			durationInNano = endTime - startTime;
		}
		
		if(bestAlgo.equals("Quick Sort")) {
			QuickSort quick = new QuickSort();
			//QuickSortImproved quick = new QuickSortImproved();
			long startTime = System.nanoTime();
			quick.quickSort(arr);
			long endTime = System.nanoTime();
			mov = quick.getMov();
			comp = quick.getComp();
			durationInNano = endTime - startTime;
		}
		
		if(bestAlgo.equals("Radix Sort")) {
			RadixSort radix = new RadixSort();
			//RadixSortImproved radix = new RadixSortImproved();
			long startTime = System.nanoTime();
			radix.radixSort(arr, size);
			long endTime = System.nanoTime();
			mov = radix.getMov();
			comp = radix.getComp();
			durationInNano = endTime - startTime;
		}
	}

	private void typeOfList(int[] arr, int size) {
		typeOfListNumber(arr,size);
		Boolean wasUnsorted = false;
		//add a bubble sort then resort then recalculate
		if(typeOfList < 0.4) {
			if(typeOfList == 0)
				wasUnsorted = true;
			long startTime = System.nanoTime();
			flipList(arr, size);
			long endTime = System.nanoTime();
			durationOfFlip = endTime - startTime;
			typeOfListNumber(arr, size);
		}
		
		if(typeOfList >= 0.99) {
			bestAlgo = "Flipped The List";
			typeList = "Ordered";
			if(wasUnsorted) {
				typeList = "reversed";
				durationInNano = durationOfFlip;
			}
			
		}
		
		if(typeOfList >= .9 && typeOfList < .99) {
			bestAlgo = "Insertion Sort";
			typeList = "In Order";
		}
		
		else if(typeOfList > .75 && typeOfList < .9) {
			bestAlgo = "Quick Sort";
			typeList = "Almost Order";
		}
		else if(typeOfList >= .25 && typeOfList <= .75) {
			bestAlgo = "Radix Sort";
			typeList = "Random";
		}
		else if(typeOfList < .25) {
			bestAlgo = "Quick Sort";
			typeList = "Reverse";
		}
		
		System.out.println(bestAlgo);
	}

	private void flipList(int[] arr, int size) {
		// TODO Auto-generated method stub
		for(int x = 0; x< (size/2); x++) {
			int temp = arr[x];
			arr[x] = arr[size - x - 1];
			arr[size - x - 1] = temp;
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

}
