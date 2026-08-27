import java.util.Scanner;

public class Main1157{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int n = sc.nextInt();
	    
        for(int i = n; i > 0; i--){
            if(n%i==0){
                System.out.println(n/i);
            }
        }
	}
}
