import java.util.Scanner;

public class Main1156{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    double S = 0;
	    double i = 1;
	    for(int x = 1; x <= 39;x+=2){
	        S += x/i;
	        i*=2;
	    }
	    
        System.out.printf("%.2f\n", S);
	}
}
