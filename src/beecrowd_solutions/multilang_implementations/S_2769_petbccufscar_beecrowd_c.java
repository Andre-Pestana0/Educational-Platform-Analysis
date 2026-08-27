import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int N = scanner.nextInt();

            int[] a1 = new int[N];
            int[] a2 = new int[N];
            int[] t1 = new int[N];
            int[] t2 = new int[N];

            t1[0] = scanner.nextInt();
            t2[0] = scanner.nextInt();

            for (int i = 0; i < N; i++) {
                a1[i] = scanner.nextInt();
            }

            for (int i = 0; i < N; i++) {
                a2[i] = scanner.nextInt();
            }

            for (int i = 1; i < N; i++) {
                t1[i] = scanner.nextInt();
            }

            for (int i = 1; i < N; i++) {
                t2[i] = scanner.nextInt();
            }

            int x1 = scanner.nextInt();
            int x2 = scanner.nextInt();

            int soma1 = t1[0] + a1[0];
            int soma2 = t2[0] + a2[0];

            for (int i = 1; i < N; i++) {
                int aux1 = soma1;
                int aux2 = soma2;

                if (aux1 + a1[i] > aux2 + t2[i] + a1[i]) {
                    soma1 = aux2 + t2[i] + a1[i];
                } else {
                    soma1 += a1[i];
                }

                if (aux2 + a2[i] > aux1 + t1[i] + a2[i]) {
                    soma2 = aux1 + t1[i] + a2[i];
                } else {
                    soma2 += a2[i];
                }
            }

            if (soma1 + x1 < soma2 + x2) {
                soma1 += x1;
                System.out.println(soma1);
            } else {
                soma2 += x2;
                System.out.println(soma2);
            }
        }

        scanner.close();
    }
}