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
        int n = Integer.parseInt(st.nextToken());

        int[] trees = new int[n];
        st = ensureTokens(st, reader);
        for (int i = 0; i < n; ++i) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        // Ordena o vetor em ordem crescente
        Arrays.sort(trees);

        // O maior elemento estará no final do array (simulando ordem decrescente)
        int most = trees[n - 1];

        for (int i = 1; i < n; ++i) {
            most--;
            // Acessa de trás para frente para corresponder à ordem decrescente
            int current = trees[n - 1 - i];
            if (current > most) {
                most = current;
            }
        }

        System.out.println(most + n + 1);
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