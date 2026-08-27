import java.util.Scanner;

public class Main1177{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int T = sc.nextInt();
	    int[] n = new int[1000];
        int i = 0;
	    
	    
	    while(i < 1000){
	        for(int j = 0; j < T && i < 1000; j++){
	            System.out.println("N["+i+"] = "+ (n[i]=j));
	            i++;
	        }
	    }
	    
        
	}
}