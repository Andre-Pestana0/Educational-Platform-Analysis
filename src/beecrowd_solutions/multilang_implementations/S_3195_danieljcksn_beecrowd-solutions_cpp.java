import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st = ensureTokens(st, reader);
            int n = Integer.parseInt(st.nextToken());

            long[] items = new long[n];
            st = ensureTokens(st, reader);
            for (int i = 0; i < n; ++i) {
                items[i] = Long.parseLong(st.nextToken());
            }

            // Ordena o vetor em ordem crescente
            Arrays.sort(items);

            long ans = 0;

            // Percorre o vetor de trás para frente para simular a ordem decrescente (rbegin/rend)
            for (int i = 0; i < n; ++i) {
                if ((i + 1) % 3 == 0) {
                    ans += items[n - 1 - i];
                }
            }

            sb.append(ans).append('\n');
        }

        System.out.print(sb.toString());
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