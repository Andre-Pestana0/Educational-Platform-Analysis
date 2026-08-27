import java.util.Scanner;

public class Main1144{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int n = sc.nextInt();
	    
	    /*
	    1 1^2   1^3   = 1 1 1
	    1 1^2+1 1^3+1 = 1 2 2
	    2 2^2   2^3   = 2 4 8
	    2 2^2+1 2^3+1 = 2 5 9
	    */
	    
	   for(int i = 1; i <= n; i++){
            System.out.println(i+" "+(int)Math.pow(i, 2)+" "+(int)Math.pow(i, 3));
            System.out.println(i+" "+(int)(Math.pow(i, 2)+1)+" "+(int)(Math.pow(i, 3)+1));
	    }
	}
}
