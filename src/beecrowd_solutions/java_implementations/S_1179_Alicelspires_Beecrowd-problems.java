import java.util.Scanner;

public class Main1179{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int[] pares = new int[5];
	    int[] impares = new int[5];
	    int tamP = 0;
	    int tamI = 0;
	    
        for(int j = 0; j < 15; j++){
            int r = sc.nextInt();
            if(r%2==0){
                pares[tamP] = r;
                tamP++;
	        } else {
	            impares[tamI] = r; 
	            tamI++;
	        }
	        
	        if(tamP == 5){
	            for(int k = 0; k < 5; k++){
	                System.out.println("par["+k+"] = "+pares[k]);
	            }
	            pares = new int[5];
	            tamP = 0;
	        }
	        if(tamI == 5){
	            for(int k = 0; k < 5; k++){
	                System.out.println("impar["+k+"] = "+impares[k]);
	            }
	            impares = new int[5];
	            tamI = 0;
	        }
        }
	    
	    for(int k = 0; k < tamI; k++){
    	    System.out.println("impar["+k+"] = "+impares[k]);   	                
    	}
	    for(int k = 0; k < tamP; k++){
            System.out.println("par["+k+"] = "+pares[k]);
    	}
        
	}
}
