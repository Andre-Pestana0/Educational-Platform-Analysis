import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static boolean verificaDespojado(long n) {
        if (n == 1) {
            return false;
        }

        boolean primo = true;
        long limite = (long) Math.sqrt(n);

        for (long i = 2; i <= limite; i++) {
            if (n % (i * i) == 0) {
                return false;
            }
            if (n % i == 0) {
                primo = false;
            }
        }

        // Se não for primo, e não for divisível por nenhum quadrado perfeito, é despojado.
        return !primo;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (line == null || line.trim().isEmpty()) {
            return;
        }

        long n = Long.parseLong(line.trim());
        int despojados = 0;
        long limite = (long) Math.sqrt(n);

        for (long i = 1; i <= limite; i++) {
            if (n % i == 0) {
                if (verificaDespojado(i)) {
                    despojados++;
                }

                // Para cada divisor i encontrado, existe o divisor complementar n/i.
                // Evita contar duas vezes caso i == n/i (ex: i = 3 em n = 9).
                if (i != n / i) {
                    if (verificaDespojado(n / i)) {
                        despojados++;
                    }
                }
            }
        }

        System.out.println(despojados);
    }
}