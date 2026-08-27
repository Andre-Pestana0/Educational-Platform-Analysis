import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    // Função para calcular o Máximo Divisor Comum (MDC) usando recursão
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Função para calcular o Mínimo Múltiplo Comum (MMC)
    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    // Função para calcular o MMC de um array de elementos
    public static long lcmArray(long[] ciclos) {
        long mmc = lcm(ciclos[0], ciclos[1]);

        for (int i = 2; i < ciclos.length; ++i) {
            mmc = lcm(mmc, ciclos[i]);
        }

        return mmc;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (line == null || line.trim().isEmpty()) {
            return;
        }

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        long l = Long.parseLong(st.nextToken());

        long[] ciclos = new long[n];

        for (int i = 0; i < n; ++i) {
            while (st == null || !st.hasMoreTokens()) {
                line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }
            if (st != null && st.hasMoreTokens()) {
                ciclos[i] = Long.parseLong(st.nextToken());
            }
        }

        long mmc = lcmArray(ciclos);

        // Representação do pair<ull, ull> greater = {first, second}
        long greaterFirst = 0;
        long greaterSecond = 0;

        for (long i = 1; i <= l; ++i) {
            long currentMmc = lcm(mmc, i);

            if (currentMmc <= l) {
                if (currentMmc > greaterFirst) {
                    greaterFirst = currentMmc;
                    greaterSecond = i;
                }
            }
        }

        System.out.println(greaterSecond);
    }
}