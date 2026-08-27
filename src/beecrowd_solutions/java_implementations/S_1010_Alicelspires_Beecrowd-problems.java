import java.util.Scanner;

public class Main1010{
	public static void main(String[] args){
	    Scanner scanner = new Scanner(System.in);
	    int cod01 = scanner.nextInt();
	    int n01 = scanner.nextInt();
	    double valor01 = scanner.nextDouble();
	    
	    int cod02 = scanner.nextInt();
	    int n02 = scanner.nextInt();
	    double valor02 = scanner.nextDouble();
	    
	    double valor = valor01*n01+valor02*n02;
	    
		System.out.printf("VALOR A PAGAR: R$ %.2f\n", valor);
	}
}