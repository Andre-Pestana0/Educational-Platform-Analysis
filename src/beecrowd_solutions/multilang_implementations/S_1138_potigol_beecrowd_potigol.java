import java.util.Scanner;

public class Main {

    public static int digitos(int n, int d) {
        int resultado = 0;
        int potencia = 1;
        int resto = 0;
        int x = 0;
        int count = n;

        while (count != 0) {
            x = count % 10;
            count = count / 10;

            if (x > d) {
                resultado += (count + 1) * potencia;
            } else {
                resultado += count * potencia;
            }

            if (x == d) {
                resultado += resto + 1;
            }

            if (d == 0) {
                resultado -= potencia;
            }

            resto += potencia * x;
            potencia *= 10;
        }

        return resultado;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        while (a != 0 && b != 0) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i <= 9; i++) {
                int countDigits = digitos(b, i) - digitos(a - 1, i);
                sb.append(countDigits);
                if (i < 9) {
                    sb.append(" ");
                }
            }

            System.out.println(sb.toString());

            if (scanner.hasNextInt()) {
                a = scanner.nextInt();
                b = scanner.nextInt();
            } else {
                break;
            }
        }

        scanner.close();
    }
}