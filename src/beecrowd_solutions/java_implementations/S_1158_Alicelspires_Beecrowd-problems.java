import java.util.Scanner;

public class Main1158{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int n = sc.nextInt();
	    for(int i = 0; i < n; i++){
	        int x = sc.nextInt();
	        int y = sc.nextInt();
	        
	        int j = 0;
	        int soma = 0;
	        
	        while(j < y){
	            if(x%2!=0){
	                soma+=x;
	                j++;
	            }
	            x++;
	        }
	        System.out.println(soma);
	    }
	}
}
