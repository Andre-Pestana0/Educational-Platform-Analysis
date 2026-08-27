import java.util.Locale;
import java.util.Scanner;

public class Main {

    // Função de exponenciação recursiva conforme o pseudocódigo
    public static double exponenciacao(double a, int cont) {
        if (cont == 0) {
            return 1.0;
        } else {
            return a * exponenciacao(a, cont - 1);
        }
    }

    // Função que calcula a probabilidade
    public static double probabilidade(int ev1, int ev2, int at) {
        double res = 0.0;

        if (at == 3) {
            res = (double) ev1 / (ev1 + ev2);
        } else {
            double dado = 1.0 - (6.0 - at) / 6.0;
            dado = (1.0 - dado) / dado;
            
            double num = 1.0 - exponenciacao(dado, ev1);
            double den = 1.0 - exponenciacao(dado, ev1 + ev2);
            res = num / den;
        }
        
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Define o padrão US para formatar com ponto decimal (ex: 50.0)
        scanner.useLocale(Locale.US);

        while (scanner.hasNextInt()) {
            int ev1 = scanner.nextInt();
            int ev2 = scanner.nextInt();
            int at = scanner.nextInt();
            int d = scanner.nextInt();

            // Condição de parada: todos iguais a 0
            if (ev1 == 0 && ev2 == 0 && at == 0 && d == 0) {
                break;
            }

            // Divisão arredondada para cima de ev1 por d
            int aux = ev1;
            ev1 = 0;
            while (aux > 0) {
                aux -= d;
                ev1++;
            }

            // Divisão arredondada para cima de ev2 por d
            aux = ev2;
            ev2 = 0;
            while (aux > 0) {
                aux -= d;
                ev2++;
            }

            double p = probabilidade(ev1, ev2, at);

            // Imprime o resultado formatado com 1 casa decimal
            System.out.printf(Locale.US, "%.1f\n", p * 100);
        }

        scanner.close();
    }
}