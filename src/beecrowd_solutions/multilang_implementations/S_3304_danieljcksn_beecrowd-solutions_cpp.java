import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static boolean search(long x, long[] numbers) {
        int l = 0, r = numbers.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (numbers[m] == x)
                return true;

            if (numbers[m] < x)
                l = m + 1;
            else
                r = m - 1;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder sb = new StringBuilder();

        // Número total de elementos para até 8 dígitos (2^1 + 2^2 + ... + 2^8 = 510)
        long[] numbers = new long[510];
        int index = 0;

        // Pré-processamento sem Math.pow()
        for (int digits = 1; digits <= 8; ++digits) {
            int limit = 1 << digits;
            for (int mask = 0; mask < limit; mask++) {
                long newNum = 0;
                long p10 = 1;

                for (int i = 0; i < digits; ++i) {
                    if ((mask & (1 << i)) != 0) {
                        newNum += 7 * p10;
                    } else {
                        newNum += 4 * p10;
                    }
                    p10 *= 10;
                }
                numbers[index++] = newNum;
            }
        }

        // Garante que os números estejam estritamente ordenados para a busca binária
        Arrays.sort(numbers);

        String token;
        while ((token = scanner.next()) != null) {
            long n = Long.parseLong(token);

            if (search(n, numbers)) {
                sb.append("sortudo\n");
            } else {
                boolean ans = false;

                for (int i = 0; i < numbers.length; ++i) {
                    if (numbers[i] > n)
                        break;
                    if (n % numbers[i] == 0) {
                        ans = true;
                        break;
                    }
                }

                if (ans) {
                    sb.append("quase sortudo\n");
                } else {
                    sb.append("azarado\n");
                }
            }
        }

        // Imprime o resultado acumulado de uma só vez
        System.out.print(sb);
    }

    static class FastScanner {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreElements()) {
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
    }
}