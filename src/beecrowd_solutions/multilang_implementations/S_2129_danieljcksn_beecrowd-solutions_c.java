import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static long nonzero(long n) {
        if (n == 0 || n == 1)
            return 1;

        long x = n / 5;
        long y = n % 5;

        // Obtém o último dígito de 2^x (ciclo das potências de 2 mod 10: 6, 2, 4, 8)
        long potX;
        if (x % 4 == 0) {
            potX = 6; // Para x > 0 e múltiplo de 4, 2^x mod 10 é 6
        } else if (x % 4 == 1) {
            potX = 2;
        } else if (x % 4 == 2) {
            potX = 4;
        } else {
            potX = 8;
        }

        // Obtém o último dígito do fatorial de Y (y!)
        long lastY;
        switch ((int) y) {
            case 0:
            case 1:
                lastY = 1;
                break;
            case 2:
                lastY = 2;
                break;
            case 3:
                lastY = 6;
                break;
            case 4:
                lastY = 4;
                break;
            default:
                lastY = 0;
        }

        // Retorna o produto mantendo apenas o último dígito não-nulo
        return (potX * nonzero(x) % 10 * lastY) % 10;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        long instancia = 1;

        while (true) {
            Long nToken = scanner.nextLong();
            if (nToken == null) break;

            long n = nToken;

            output.append("Instancia ").append(instancia).append("\n");
            instancia++;

            long result = nonzero(n) % 10;

            output.append(result).append("\n\n");
        }

        System.out.print(output);
    }

    // Leitor rápido de I/O para evitar Time Limit Exceeded (TLE)
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        Long nextLong() {
            String s = next();
            if (s == null) return null;
            return Long.parseLong(s);
        }
    }
}