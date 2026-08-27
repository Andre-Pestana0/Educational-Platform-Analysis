import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);
        FastScanner scanner = new FastScanner();

        String firstToken = scanner.next();
        if (firstToken == null) return;

        double[] peso = new double[4];
        peso[0] = Double.parseDouble(firstToken);
        peso[1] = scanner.nextDouble();
        peso[2] = scanner.nextDouble();
        peso[3] = scanner.nextDouble();

        Arrays.sort(peso);

        boolean ans = false;

        if (Math.abs(peso[3] - (peso[0] + peso[1] + peso[2])) < 1e-9)
            ans = true;
        if (Math.abs((peso[0] + peso[1]) - (peso[2] + peso[3])) < 1e-9)
            ans = true;
        if (Math.abs((peso[0] + peso[2]) - (peso[1] + peso[3])) < 1e-9)
            ans = true;
        if (Math.abs((peso[0] + peso[3]) - (peso[1] + peso[2])) < 1e-9)
            ans = true;

        System.out.println(ans ? "YES" : "NO");
    }

    // Leitor rápido de entrada
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}