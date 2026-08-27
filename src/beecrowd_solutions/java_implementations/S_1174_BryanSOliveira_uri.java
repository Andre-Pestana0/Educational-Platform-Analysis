import java.util.Scanner;

public class Uri1174 {
    public static void main(String[] args) {
        
        // ENTRADA
        Scanner in = new Scanner(System.in);
        double vetor [] = new double [10];

        // PROCESSAMENTO
        for (var i = 0; i < 100; i++) {
            vetor[i] = in.nextDouble();
        }

        for (var i = 0; i < vetor.length; i++) {
            if (vetor[i] <= 10) {
                System.out.printf("A[%d] = %.1f\n", i, vetor[i]);
            }
        }

        in.close();
        // SAÍDA
        
    }
}
