import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int instancia = 1;

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] vet = new int[n];

            for (int i = 0; i < n; i++) {
                vet[i] = sc.nextInt();
            }

            int soma = vet[0];
            boolean flag = false;
            int iFound = 0;

            for (int i = 1; i < n; i++) {
                if (soma == vet[i]) {
                    flag = true;
                    iFound = i;
                    break;
                }
                soma += vet[i];
            }

            System.out.printf("Instancia %d\n", instancia);

            if (vet[0] == 0) {
                System.out.println(0);
            } else if (flag) {
                System.out.println(vet[iFound]);
            } else {
                System.out.println("nao achei");
            }

            System.out.println();
            instancia++;
        }

        sc.close();
    }
}