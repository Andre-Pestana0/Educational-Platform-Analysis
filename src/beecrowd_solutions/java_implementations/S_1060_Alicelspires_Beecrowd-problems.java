import java.util.Scanner;

public class Main1060
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        
        double num = sc.nextDouble();
        int i = 1; 
        int qtd = 1;
        
        while(i < 6){
            if(num >= 0){
                qtd++;
            }
            i++;
            num = sc.nextDouble();
        } 
        
        System.out.println(qtd +" valores positivos");
        
	}
} 