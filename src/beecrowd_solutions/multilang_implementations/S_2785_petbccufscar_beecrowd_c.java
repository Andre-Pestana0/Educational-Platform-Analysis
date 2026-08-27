import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;
        int tamMatriz = scanner.nextInt();

        int[][] matriz = new int[tamMatriz][tamMatriz];
        int[][] maux = new int[tamMatriz][tamMatriz];

        for (int i = 0; i < tamMatriz; i++) {
            for (int j = 0; j < tamMatriz; j++) {
                matriz[i][j] = scanner.nextInt();
            }
        }

        // Caso base para matriz 1x1
        if (tamMatriz == 1) {
            System.out.println(matriz[0][0]);
            scanner.close();
            return;
        }

        for (int i = 0; i < tamMatriz; i++) {
            maux[0][i] = matriz[0][i];
        }

        int i = 1;
        int inicio = 0;
        int fim = 1;

        while (i < tamMatriz - 1) {
            for (int j = inicio; j <= fim; j++) {
                maux[i][inicio] += matriz[i][j];
            }

            if (maux[i - 1][inicio] < maux[i - 1][inicio + 1]) {
                maux[i][inicio] += maux[i - 1][inicio];
            } else {
                maux[i][inicio] += maux[i - 1][inicio + 1];
            }

            inicio++;
            fim++;

            if (fim == tamMatriz) {
                i++;
                inicio = 0;
                fim = i;
            }
        }

        int soma = (maux[tamMatriz - 2][0] < maux[tamMatriz - 2][1]) 
                 ? maux[tamMatriz - 2][0] 
                 : maux[tamMatriz - 2][1];

        for (int k = 0; k < tamMatriz; k++) {
            soma += matriz[tamMatriz - 1][k];
        }

        System.out.println(soma);
        scanner.close();
    }
}