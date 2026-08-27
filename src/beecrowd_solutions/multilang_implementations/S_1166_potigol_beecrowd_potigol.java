import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Pré-calcula os valores de 1 até 50 (usando tamanho 51 para manter o índice base 1)
        int[] bolas = new int[51];
        for (int i = 1; i <= 50; i++) {
            bolas[i] = (i + 1) * (i + 1) / 2 - 1;
        }

        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int t = scanner.nextInt();

            for (int i = 0; i < t; i++) {
                int n = scanner.nextInt();
                System.out.println(bolas[n]);
            }
        }

        scanner.close();
    }
}