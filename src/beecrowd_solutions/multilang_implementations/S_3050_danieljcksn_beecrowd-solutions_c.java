import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        if (line == null) return;
        StringTokenizer st = new StringTokenizer(line);

        int N = Integer.parseInt(st.nextToken());
        int[] predios = new int[N];

        st = ensureTokens(st, reader);
        for (int i = 0; i < N; i++) {
            predios[i] = Integer.parseInt(st.nextToken());
        }

        int maior = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int distancia = predios[i] + (j - i) + predios[j];
                if (distancia > maior) {
                    maior = distancia;
                }
            }
        }

        System.out.println(maior);
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