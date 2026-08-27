import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = 10008;

        // Vetor que marca quais valores são primos
        boolean[] p = new boolean[N];
        Arrays.fill(p, true);

        // 0 e 1 não são primos
        p[0] = false;
        p[1] = false;

        // Crivo de Eratóstenes
        for (int n = 2; n < N; n++) {
            if ((long) n * n >= N) {
                break;
            }

            if (p[n]) {
                for (int k = 2 * n; k < N; k += n) {
                    p[k] = false;
                }
            }
        }

        // Lista com os números primos efetivamente
        List<Integer> q = new ArrayList<>();
        for (int i = 2; i < N; i++) {
            if (p[i]) {
                q.add(i);
            }
        }

        // Vetor solução para PD (Programação Dinâmica)
        int[] D = new int[3 * N];
        Arrays.fill(D, N);

        // Construção do vetor solução
        for (int n : q) {
            // O jogador da vez pode apenas dividir pelo número primo
            D[3 * n] = 1;

            // Para todos os números menores que esse, até o próximo primo
            for (int l = n - 1; l > 1; l--) {
                if (p[l]) {
                    break;
                }

                // O número 'l' não é primo, inicialmente a melhor jogada é somar 1
                D[3 * l + 0] = D[3 * (l + 1) + 2];
                D[3 * l + 1] = D[3 * (l + 1) + 0];
                D[3 * l + 2] = D[3 * (l + 1) + 1];

                // Mas pode ser que 'l' seja múltiplo de um primo
                for (int k : q) {
                    if (l % k == 0) {
                        int opt = Math.min(l / k, D[3 * (l / k) + 2]);
                        if (opt <= D[3 * l]) {
                            D[3 * l + 0] = opt;
                            D[3 * l + 1] = D[3 * (l / k) + 0];
                            D[3 * l + 2] = D[3 * (l / k) + 1];
                        }
                    }
                }
            }
        }

        // Substituindo os números restantes pelo número inicial
        for (int n = 1; n < N; n++) {
            D[3 * n + 0] = Math.min(n, D[3 * n + 0]);
            D[3 * n + 1] = Math.min(n, D[3 * n + 1]);
            D[3 * n + 2] = Math.min(n, D[3 * n + 2]);
        }

        // Leitura e Solução
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int numRounds = Integer.parseInt(st.nextToken());

        long o = 0;
        long e = 0;
        long i = 0;

        for (int r = 0; r < numRounds; r++) {
            st = ensureTokens(st, reader);

            char c = st.nextToken().charAt(0);
            int k = Integer.parseInt(st.nextToken());

            if (c == 'O') {
                o += D[3 * k + 0];
                e += D[3 * k + 1];
                i += D[3 * k + 2];
            } else if (c == 'E') {
                e += D[3 * k + 0];
                i += D[3 * k + 1];
                o += D[3 * k + 2];
            } else {
                i += D[3 * k + 0];
                o += D[3 * k + 1];
                e += D[3 * k + 2];
            }
        }

        System.out.println(o + " " + e + " " + i);
    }

    private static StringTokenizer ensureTokens(StringTokenizer st, BufferedReader reader) throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) break;
            st = new StringTokenizer(line);
        }
        return st;
    }
}