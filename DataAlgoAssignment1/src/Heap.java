/*
 * Professor has given the Heap algorithm
 * Thais Campanac-Climent added the probes and get() functions
 */
public class Heap<E extends Comparable<E>> {
	
	//to calculate the number of movements
	public static int mov = 0;
	//to calculate the number of comparisons
	public static int comp = 0;
		
	private java.util.ArrayList<E> list = new java.util.ArrayList<>();

	public Heap() {
	}

	public Heap(E[] objects) {
		for(int i = 0; i < objects.length; i++) {
			add(objects[i]);
		}
	}
	
	public void add(E newObject) {
		list.add(newObject);
		int currentIndex = list.size() - 1;
		
		while(currentIndex > 0) {
			comp++;
			int parentIndex = (currentIndex - 1) / 2;
			
			comp++;
			if(list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
				E temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				mov++;
				list.set(parentIndex, temp);
				mov++;
			}
			else {
				break;
			}
			
			currentIndex = parentIndex;
		}
	}
	
	public E remove() {
		comp++;
		if(list.size() == 0) return null;
		
		E removedObject = list.get(0);
		list.set(0, list.get(list.size() - 1));
		mov++;
		list.remove(list.size() - 1);
		mov++;
		
		int currentIndex = 0;
		while (currentIndex < list.size()) {
			comp++;
			int leftChildIndex = 2 * currentIndex + 1;
			int rightChildIndex = 2 * currentIndex + 2;
			
			comp++;
			if(leftChildIndex >= list.size()) break;
			int maxIndex = leftChildIndex;
			
			comp++;
			if(rightChildIndex < list.size()) {
				comp++;
				if(list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
					maxIndex = rightChildIndex;
				}
			}
			
			comp++;
			if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
				E temp = list.get(maxIndex);
				mov++;
				list.set(maxIndex, list.get(currentIndex));
				mov++;
				list.set(currentIndex, temp);
				currentIndex = maxIndex;
			}
			else {
				break;
			}
		}
		return removedObject;
	}
	
	public int getSize() {
		return list.size();
	}
	
	//returning all probes to the other class
	public int getMov() {
		return mov;
	}
	public int getComp() {
		return comp;
	}
	
	//reseting all probes 
	public void resetMov() {
		mov = 0;
	}
	public void resetComp() {
		comp = 0;
	}
}
