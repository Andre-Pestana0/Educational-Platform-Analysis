import java.util.Scanner;

public class Main1043
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        
        double A = sc.nextDouble();
        double B = sc.nextDouble();
        double C = sc.nextDouble();
        
        if(A + B > C && C + B > A && A + C > B){
            System.out.printf("Perimetro = %.1f\n", A+B+C);
        } else {
            System.out.printf("Area = %.1f\n", (A+B)*C/2);
        }
	}
} 