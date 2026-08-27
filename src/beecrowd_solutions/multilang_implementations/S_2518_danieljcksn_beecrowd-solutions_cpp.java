import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            double n = Double.parseDouble(st.nextToken());

            while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            double h = Double.parseDouble(st.nextToken());

            while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            double c = Double.parseDouble(st.nextToken());

            while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            double l = Double.parseDouble(st.nextToken());

            double x = Math.sqrt(h * h + c * c);
            double ans = l * x * n;

            // Equivale ao fixed e setprecision(4)
            System.out.printf(Locale.US, "%.4f\n", ans / 10000.0);
        }
    }
}