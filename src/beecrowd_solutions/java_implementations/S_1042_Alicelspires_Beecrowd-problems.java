import java.util.Scanner;

public class Main1042
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int n3 = sc.nextInt();
        
        int[] lista = {n1, n2, n3};
        
        for(int i = 0; i < lista.length-1; i++){
            for(int j = 0; j < lista.length-1-i; j++){
                if(lista[j] > lista[j+1]){
                    int x = lista[j];
                    lista[j] = lista[j+1];
                    lista[j+1]=x;
                }
            }
        }
        for(int num : lista){
            System.out.println(num);
        }
        
        System.out.println();
        System.out.println(n1+"\n"+n2+"\n"+n3);
	}
} 