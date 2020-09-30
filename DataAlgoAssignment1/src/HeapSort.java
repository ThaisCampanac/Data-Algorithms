/*
 * Professor has given the Heap algorithm
 * Thais Campanac-Climent added the probes and get() functions
 */
public class HeapSort {

	public HeapSort() {
		// TODO Auto-generated constructor stub
	}
	
	public static <E extends Comparable<E>> void heapSort(E[] list) {
		Heap<E> heap = new Heap<>();
		
		for(int i = 0; i < list.length; i++) {
			heap.add(list[i]);
		}

		
		for(int i = list.length - 1; i >= 0; i--) {
			list[i] = heap.remove();
		}
	}
}
