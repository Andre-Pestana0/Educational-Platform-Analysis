// Sequencia logica 2

import java.util.Scanner;

public class Main1145{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int x = sc.nextInt();
	    int y = sc.nextInt();
	    int i = 1;
	    
	    while(i<=y){
	        for(int j = 1; j <= x; j++){
	            if(j!=1) System.out.print(" ");
	            System.out.print(i);
	            i++;
	            if(i > y) break;
	        }
	        System.out.print("\n");
	    }
	}
}
