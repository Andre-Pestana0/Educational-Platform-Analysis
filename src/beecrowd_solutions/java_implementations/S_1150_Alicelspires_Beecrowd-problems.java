import java.util.Scanner;

public class Main1150{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int x = sc.nextInt();
	    int z = sc.nextInt();
	    
	    while(z <= x){
	        z = sc.nextInt();
	    }
	    
	    int i = 1;
	    int soma = x;
	    x++;
	    while(soma <= z){
	        soma += x;
	        x++;
	        i++;
	    }
	    
        System.out.println(i);
	}
}
