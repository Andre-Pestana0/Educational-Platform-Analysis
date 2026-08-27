import java.util.Scanner;

public class Main1009{
	public static void main(String[] args){
	    Scanner scanner = new Scanner(System.in);
	    String name = scanner.nextLine();
	    double salarioFixo = scanner.nextDouble();
	    double vendaMes = scanner.nextDouble();
	    double total = salarioFixo + (vendaMes*0.15);
	    
		System.out.printf("TOTAL = R$ %.2f\n", total);
	}
}