package Finished;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ProgrammingQuestion1 {
	public static long getNumOfInversions(int[] array) {
		if (array == null || array.length < 1)
			return 0;
		return getNumOfInversions(array, 0, array.length - 1);
	}
	
	public static long getNumOfInversions(int[] array, int beg, int end) {
		if (beg == end)
			return 0;
		if (end - beg == 1) {
			if (array[end] < array[beg]) {
				int tmp = array[beg];
				array[beg] = array[end];
				array[end] = tmp;
				return 1;
			}
			else
				return 0;
		}
		int mid = beg + (end - beg + 1) / 2;
		long result = 0;
		result += getNumOfInversions(array, beg, mid - 1);
		result += getNumOfInversions(array, mid, end);
		int[] arrayCopy = new int[end - beg + 1];
		int i1 = beg;
		int i2 = mid;
		for (int i = 0; i < arrayCopy.length; ++i) {
			if (i1 >= mid)
				arrayCopy[i] = array[i2++];
			else if (i2 >= end + 1) {
				arrayCopy[i] = array[i1++];
			}
			else if (array[i1] <= array[i2])
				arrayCopy[i] = array[i1++];
			else {
				arrayCopy[i] = array[i2++];
				result = result + mid - i1;
			}
		}
		System.arraycopy(arrayCopy, 0, array, beg, end - beg + 1);
		return result;
	}
	
	@Test
	public void tc01() {
		int[] array = {1, 3, 5, 2, 4, 6};
		assertTrue(getNumOfInversions(array) == 3);
	}
	
	@Test
	public void tc02() {
		int[] array = {6, 5, 4, 3, 2, 1};
		assertTrue(getNumOfInversions(array) == 15);
	}
	
	@Test
	public void tc03() {
		int[] array = {2,1,3};
		assertTrue(getNumOfInversions(array) == 1);
	}
	
	@Test
	public void tc04() {
		int[] array = {10, 9, 8, 7, 6, 5,4,3,2,1};
		long result = getNumOfInversions(array);
		assertTrue(result == 45);
	}
	
	public static void main(String[] args) {
		BufferedReader br;
		List<Integer> list = new ArrayList<Integer>();
		try {
			br = new BufferedReader(new FileReader(
					"/Users/zhili/Development/CourseraAlgorithm/src/Finished/IntegerArray.txt"));
			String line;
			while ((line = br.readLine()) != null) {
			   list.add(Integer.parseInt(line));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); ++i)
			array[i] = list.get(i);
		long result = getNumOfInversions(array);
		System.out.println(result);
	}
}
