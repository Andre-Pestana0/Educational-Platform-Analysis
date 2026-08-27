import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        String[] linhas = new String[n];
        int minTam = 1001;
        int minTamI = 0;

        for (int i = n - 1; i >= 0; i--) {
            linhas[i] = scanner.next();
            if (linhas[i].length() < minTam) {
                minTamI = i;
                minTam = linhas[i].length();
            }
        }

        int achou = 0;
        String aux = "";

        for (int i = minTam; i > 0 && achou == 0; i--) {
            for (int k = 0; k + i <= minTam && achou == 0; k++) {
                // Equivalente ao strncpy do C obtendo a substring
                aux = linhas[minTamI].substring(k, k + i);
                achou = 1;

                for (int j = 0; j < n && achou == 1; j++) {
                    // Equivalente ao strstr em C
                    if (!linhas[j].contains(aux)) {
                        achou = 0;
                    }
                }
            }
        }

        System.out.println(aux);

        scanner.close();
    }
}