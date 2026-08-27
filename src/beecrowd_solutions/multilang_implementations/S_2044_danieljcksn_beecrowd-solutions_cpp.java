import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        while (true) {
            Integer nToken = scanner.nextInt();
            if (nToken == null || nToken == -1) break;

            int n = nToken;
            int acumulado = 0;
            int visitas = 0;

            for (int i = 0; i < n; i++) {
                int p = scanner.nextInt();
                acumulado += p;

                if (acumulado % 100 == 0) {
                    visitas++;
                    acumulado = 0;
                }
            }

            output.append(visitas).append("\n");
        }

        System.out.print(output);
    }

    // Leitor rápido de I/O otimizado
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