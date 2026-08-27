import java.util.Scanner;

public class Main1159{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int x = sc.nextInt();
	    int k = x;
	    while(x!=0){
	        int j = 0;
	        int soma = 0;
	        
	        while(j < 5){
	            if(x%2==0){
	                soma+=x;
	                j++;
	            }
	            x++;
	        }
	        System.out.println(soma);
	        x = sc.nextInt();
	    }
	}
}
