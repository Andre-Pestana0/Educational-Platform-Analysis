import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static int[][] dp = new int[1001][2001];

    // Retorna o maior valor para uma mochila ilimitada de capacidade W
    public static int mb(int W, int[] wt, int[] val, int n) {
        // Caso base: se o número de elementos for 0
        if (n == 0)
            return 0;

        // Retorna se já foi calculado
        if (dp[n][W] != -1)
            return dp[n][W];

        // Se o peso do item atual for maior que a capacidade W
        if (wt[n] > W) {
            dp[n][W] = mb(W, wt, val, n - 1);
            return dp[n][W];
        } else {
            // Máximo entre incluir o item (podendo repetir n) e não incluir
            dp[n][W] = Math.max(val[n] + mb(W - wt[n], wt, val, n), mb(W, wt, val, n - 1));
            return dp[n][W];
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        while (true) {
            Integer nTokens = scanner.nextInt();
            Integer pmaxTokens = scanner.nextInt();

            if (nTokens == null || pmaxTokens == null) break;

            int n = nTokens;
            int pmax = pmaxTokens;

            int[] pesos = new int[n + 1];
            int[] valores = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                pesos[i] = scanner.nextInt();
                valores[i] = scanner.nextInt();
            }

            // Preenche a matriz dp com -1
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], -1);
            }

            output.append(mb(pmax, pesos, valores, n)).append('\n');
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

        Integer nextInt() {
            String s = next();
            if (s == null) return null;
            return Integer.parseInt(s);
        }
    }
}