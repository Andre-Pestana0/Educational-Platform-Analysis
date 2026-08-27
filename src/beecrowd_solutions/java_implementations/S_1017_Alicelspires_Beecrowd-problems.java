import java.util.Scanner;

public class Main1017{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double h = sc.nextDouble();
		double vm = sc.nextDouble();
		double l = h*vm/12;
		
		System.out.printf("%.3f\n", l);
		
	}
}