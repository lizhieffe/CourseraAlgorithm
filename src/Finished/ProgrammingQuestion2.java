package Finished;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ProgrammingQuestion2 {
	
	public static int getCount(int[] array, PivotChooser chooser) {
		if (array == null || array.length == 0)
			return 0;
		return getCount(array, 0, array.length - 1, chooser);
	}
	
	private static int getCount(int[] array, int beg, int end, PivotChooser chooser) {
		if (beg >= end)
			return 0;
		if (end - beg == 1) {
			if (array[beg] > array[end]) {
				Util.swap(array, beg, end);
			}
			return 1;
		}
		chooser.choose(array, beg, end);
		int result = end - beg;
		int mid = beg + 1;
		for (int i = beg + 1; i <= end; ++i) {
			if (array[i] < array[beg])
				Util.swap(array, mid++, i);
			else
				continue;
		}
		Util.swap(array, mid - 1, beg);
		result += getCount(array, beg, mid - 2, chooser);
		result += getCount(array, mid, end, chooser);
		return result;
	}
	
	public static void main(String[] args) {
		BufferedReader br;
		List<Integer> list = new ArrayList<Integer>();
		try {
			br = new BufferedReader(new FileReader(
					"/Users/zhili/Development/CourseraAlgorithm/src/Finished/QuickSort.txt"));
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
		int[] array2 = new int[array.length];
		int[] array3 = new int[array.length];
		System.arraycopy(array, 0, array2, 0, array.length);
		System.arraycopy(array, 0, array3, 0, array.length);

		System.out.println(getCount(array, new PivotChooser1()));
		System.out.println(getCount(array2, new PivotChooser2()));
		System.out.println(getCount(array3, new PivotChooser3()));
		System.out.println();
	}
}

interface PivotChooser {
	public void choose(int[] array, int beg, int end);
}

class PivotChooser1 implements PivotChooser {
	public void choose(int[] array, int beg, int end) {
	}
}

class PivotChooser2 implements PivotChooser {
	public void choose(int[] array, int beg, int end) {
		Util.swap(array, beg, end);
	}
}

class PivotChooser3 implements PivotChooser {
	public void choose(int[] array, int beg, int end) {
		int a = array[beg];
		int mid = beg + (end - beg) / 2;
		int b = array[mid];
		int c = array[end];
		if (a >= b) {
			if (b >= c)
				Util.swap(array, mid, beg);
			else if (c >= a)
				Util.swap(array, beg, beg);
			else
				Util.swap(array, end, beg);
		}
		else {
			if (a >= c)
				Util.swap(array, beg, beg);
			else if (c >= b)
				Util.swap(array, mid, beg);
			else
				Util.swap(array, end, beg);
		}
	}
}

class Util {
	public static void swap(int[] array, int i, int j) {
		if (i != j) {
			int tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		}
	}
}