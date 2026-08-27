import java.util.Scanner;
import java.util.Arrays;

public class Main1045
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        
        double[] valores = new double[3];
        valores[0] = sc.nextDouble();
        valores[1] = sc.nextDouble();
        valores[2] = sc.nextDouble();
        
        Arrays.sort(valores);
        
        double A = valores[2];
        double B = valores[1];
        double C = valores[0];
        
        if(A >= B+C){
            System.out.println("NAO FORMA TRIANGULO");
        } else if(A < B+C){
            if(A*A == B*B + C*C){
                System.out.println("TRIANGULO RETANGULO");
            } 
            if(A*A > B*B + C*C){
                System.out.println("TRIANGULO OBTUSANGULO");
            }
            if(A*A < B*B + C*C){
                System.out.println("TRIANGULO ACUTANGULO");
            }
            if(A == B && C == B){
                System.out.println("TRIANGULO EQUILATERO");
            }
            if((A != B && A == C) || (C != A && A == B) || (C != A && C == B)){
                System.out.println("TRIANGULO ISOSCELES");
            }
        }
	}
} 