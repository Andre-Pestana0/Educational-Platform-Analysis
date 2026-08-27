import java.util.Scanner;

public class Main1038{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int num = sc.nextInt();
        int qdt = sc.nextInt();
        double[][] produto = {
            {1, 4.00},
            {2, 4.50},
            {3, 5.00},
            {4, 2.00},
            {5, 1.50}
        };
        
        double valor = qdt*produto[num-1][1];
        System.out.printf("Total: R$ %.2f\n", valor);
    }
}