package sorting;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class MergeSortTest extends TestCase {
	public void testSortOnNullList() {
		List<Integer> list = null;		
		List<Integer> sortedList = MergeSort.sort(list);
		assertNull(sortedList);
	}
	
	public void testSortOnEmptyList() {
		List<Integer> list = new ArrayList<Integer>();		
		List<Integer> sortedList = MergeSort.sort(list);
		assertNotNull(sortedList);
		assertEquals(0, sortedList.size());
	}

	public void testSortOnIntegerList() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(Integer.valueOf(2));
		list.add(Integer.valueOf(-2));
		list.add(Integer.valueOf(2));
		list.add(Integer.valueOf(0));
		list.add(Integer.valueOf(-1));
		list.add(Integer.valueOf(1));
		list.add(Integer.valueOf(100));
		list.add(Integer.valueOf(99));
		list.add(Integer.valueOf(-49));		
		
		List<Integer> sortedList = MergeSort.sort(list);
		assertNotNull(sortedList);
		assertEquals(list.size(), sortedList.size());
		
		for(int i = 0; i < sortedList.size() -1; ++i) {
			assertTrue(sortedList.get(i) <= sortedList.get(i+1));
		}
	}
}
