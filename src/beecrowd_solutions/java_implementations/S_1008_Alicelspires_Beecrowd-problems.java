import java.util.Scanner;

public class Main1008{
	public static void main(String[] args){
	    Scanner scanner = new Scanner(System.in);
	    int nF = scanner.nextInt();
	    int hT = scanner.nextInt();
	    double sH = scanner.nextDouble();
	    double salario = hT * sH;
	    
		System.out.printf("NUMBER = %d\n", nF);
		System.out.printf("SALARY = U$ %.2f\n", salario);
	}
}