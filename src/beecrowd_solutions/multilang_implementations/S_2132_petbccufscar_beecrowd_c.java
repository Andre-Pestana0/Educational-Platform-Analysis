import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        int caso = 1;

        while (true) {
            String palavra = scanner.next();
            if (palavra == null) break; // Trata o EOF (End of File)

            output.append("Palavra ").append(caso).append("\n");

            int tam = palavra.length();
            long total = 0;

            for (int i = 0; i < tam; i++) {
                if (palavra.charAt(i) == 'b') {
                    // (1L << exp) é o equivalente exato e rápido de 2^(tam - i - 1)
                    total += (1L << (tam - i - 1));
                }
            }

            output.append(total).append("\n\n");
            caso++;
        }

        System.out.print(output);
    }

    // Leitor rápido de I/O para o Beecrowd / Juízes Online
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
    }
}