import java.util.Scanner;

public class Main1036{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();
        
        double delta = Math.pow(b,2)-4*a*c;
        
        if(delta < 0 || a == 0){
            System.out.println("Impossivel calcular");
        } else {
            double xp= (-b+(Math.pow(delta, 0.5)))/(2*a);
            
            double xn= (-b-(Math.pow(delta, 0.5)))/(2*a);
            
            System.out.printf("R1 = %.5f\n", xp);
            System.out.printf("R2 = %.5f\n", xn);
        }
	}
}