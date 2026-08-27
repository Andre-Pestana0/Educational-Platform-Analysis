import java.util.Scanner;

public class Main1178{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    double x = sc.nextDouble();
	    double[] n = new double[100];
        int i = 1;
        n[0]=x;
	    
	    System.out.printf("N[0] = %.4f\n",n[0]);
	    while(i < 100){
	        n[i] = n[i-1] / 2.0;
            System.out.printf("N[%d] = %.4f\n",i, n[i]);
            i++;
	    }
	     
        
	}
}
