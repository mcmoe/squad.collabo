package sorting;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {

	private static <E extends Comparable<E>> void divideList(List<E> list, List<E> left, List<E> right) {
		int size = list.size();		
		if(size >= 2) {
			int i = 0;
			int leftSize = size / 2;			
			while(i < leftSize) {
				left.add(list.get(i));
				++i;
			}			
			while(i < size) {
				right.add(list.get(i));
				++i;
			}
		}
	}
	
	private static <E extends Comparable<E>> List<E> merge(List<E> sortedLeft, List<E> sortedRight) {
		List<E>  sortedList = new ArrayList<E>();
		int j = 0;
		for(E left : sortedLeft) { 			
			while(j < sortedRight.size()) {
				if(sortedRight.get(j).compareTo(left) < 0) {
					sortedList.add(sortedRight.get(j));
					++j;
				} else {
					break;
				}				
			}			
			sortedList.add(left);
		}		
		while(j < sortedRight.size()) {
			sortedList.add(sortedRight.get(j));
			++j;
		}		
		return sortedList;
	}
	
	private static <E extends Comparable<E>> List<E> mergeSort(List<E> list) {
		if(list.size() == 1) {
			return list;
		}		
		List<E> left = new ArrayList<E>();
		List<E> right = new ArrayList<E>();		
		divideList(list, left, right);		
		
		List<E> sortedLeft = mergeSort(left);
		List<E> sortedRight = mergeSort(right);
		return merge(sortedLeft, sortedRight);
	}
	
	public static <E extends Comparable<E>> List<E> sort(List<E> list) {
		if(list == null) {
			return null;
		}
		if(list.size() == 0) {
			return list;
		}
		return mergeSort(list);
	}
	
	private static List<Integer> buildIntegerList() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(Integer.valueOf(2));
		list.add(Integer.valueOf(1));
		list.add(Integer.valueOf(3));
		list.add(Integer.valueOf(31));
		list.add(Integer.valueOf(23));
		list.add(Integer.valueOf(6));
		list.add(Integer.valueOf(4));
		list.add(Integer.valueOf(0));
		list.add(Integer.valueOf(63));
		list.add(Integer.valueOf(-2));
		list.add(Integer.valueOf(5));
		list.add(Integer.valueOf(1));
		list.add(Integer.valueOf(100));
		
		return list;
	}
	
	public static void main(String[] args) {
		List<Integer> list = buildIntegerList();		
		System.out.println(list.toString());
		List<Integer> sortedList = MergeSort.sort(list);
		System.out.println(sortedList.toString());
	}
}