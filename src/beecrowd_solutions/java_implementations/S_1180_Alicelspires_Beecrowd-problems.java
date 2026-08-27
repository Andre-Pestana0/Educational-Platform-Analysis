import java.util.Scanner;

public class Main1180{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int n = sc.nextInt();
	    int[] x = new int[n];
	
	    for(int i = 0; i < x.length; i++){
	        x[i] = sc.nextInt();
	    }
	    int menor = x[0];
	    int cont = 0;
	    
	    for(int i = 0; i < x.length-1; i++){
	        if(menor > x[i+1]){
	            menor = x[i+1];
	            cont = i+1;
	        }
	    }
	    
		System.out.println("Menor valor: "+ menor);
		System.out.println("Posicao: "+ cont);
	}
}
