package Finished;

public class CalculateBigO {
	public static double funcA(int n) {
		return Math.pow(n, 2) * Math.log(n) / Math.log(2);
	}
	
	public static double funcB(int n) {
		return Math.pow(2, n);
	}
	
	public static double funcC(int n) {
		return Math.pow(2, Math.pow(2, n));
	}
	
	public static double funcD(int n) {
		return Math.pow(n, Math.log(n) / Math.log(2));
	}
	
	public static double funcE(int n) {
		return Math.pow(n, 2);
	}
	
	public static void main(String[] args) {
		for (int i = 1; i < 100; ++i) {
			System.out.print(funcA(i) + " ");
			System.out.print(funcB(i) + " ");
			System.out.print(funcC(i) + " ");
			System.out.print(funcD(i) + " ");
			System.out.print(funcE(i) + " ");
			System.out.println();
		}
	}
}
