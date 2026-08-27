import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        final double PI = 3.14;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());

        while (n-- > 0) {
            st = ensureTokens(st, reader);
            double area = Double.parseDouble(st.nextToken());

            double r = Math.sqrt(area / (4 * PI));

            if (r <= 12) {
                System.out.printf(Locale.US, "vermelho = R$ %.2f%n", area * 0.09);
            } else if (r <= 15) {
                System.out.printf(Locale.US, "azul = R$ %.2f%n", area * 0.07);
            } else {
                System.out.printf(Locale.US, "amarelo = R$ %.2f%n", area * 0.05);
            }
        }
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