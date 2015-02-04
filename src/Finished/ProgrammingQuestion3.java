package Finished;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class ProgrammingQuestion3 {
	private static int VERTICE = 200;
	
	int[][] neighbors = new int[VERTICE][VERTICE];
	List<Integer> remainingVertice = new ArrayList<Integer>();
	Random rand = new Random(System.currentTimeMillis() % 100000047);
	
	public ProgrammingQuestion3() {
		buildRelations();
	}
	
	public int findMinCut() {
		while (remainingVertice.size() > 2) {
			int i1 = rand.nextInt(remainingVertice.size());
			int i2 = i1;
			while (i1 == i2)
				i2 = rand.nextInt(remainingVertice.size());
			int index1 = remainingVertice.get(i1);
			int index2 = remainingVertice.get(i2);
			for (int i = 0; i < VERTICE; ++i) {
				if (neighbors[index2 - 1][i] > 0 && i != i1) {
					neighbors[index1 - 1][i] += neighbors[index2 - 1][i];
					neighbors[i][index2 - 1] = 0;
					neighbors[i][index1 - 1] += neighbors[index1 - 1][i];
					neighbors[index2 - 1][i] = 0;
				}
			}
			remainingVertice.remove(i2);
			System.out.println("remaining = " + remainingVertice.size());
		}
		int result = 0;
		for (int i = 0; i < VERTICE; ++i)
			if (neighbors[remainingVertice.get(0) - 1][i] > 0)
				result += neighbors[remainingVertice.get(0) - 1][i];
		return result;
	}
	
	private void buildRelations() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(
					"/Users/zhili/Development/CourseraAlgorithm/src/Finished/kargerMinCut.txt"));
			String line;
			while ((line = br.readLine()) != null) {
			   String[] strs = line.split("\t");
			   int base = 0;
			   for (int i = 0; i < strs.length; ++i) {
				   int val = Integer.parseInt(strs[i]);
				   if (i == 0) {
					   base = val;
					   remainingVertice.add(val);
				   }
				   else
					   ++neighbors[base - 1][val - 1];
			   }
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	@Test
	public void test01() {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < Math.pow(VERTICE, 3); ++i) {
			ProgrammingQuestion3 service = new ProgrammingQuestion3();
			result = Math.min(result, service.findMinCut());
		}
		System.out.println("Final result = " + result);
	}
}
