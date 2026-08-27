import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

public class Main {

    static double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(Math.abs(x2 - x1), 2) + Math.pow(Math.abs(y2 - y1), 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());

        int x1, y1, x2, y2, x3, y3;
        double a, b, c, p, area;

        /*
         Teorema de Herão: 
         S = sqrt(p.(p-a).(p-b).(p-c))
         Onde:
         -> S: área do triângulo
         -> p: semiperímetro
         -> a, b e c: lados do triângulo
         Sendo o perímetro do triângulo a soma de todos os lados da figura, o semiperímetro representa a metade do perímetro:
         -> p = (a + b + c) / 2
        */
        while (n-- > 0) {
            st = ensureTokens(st, reader);
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            x3 = Integer.parseInt(st.nextToken());
            y3 = Integer.parseInt(st.nextToken());

            // Determina os lados do triângulo
            a = dist(x1, y1, x2, y2);
            b = dist(x2, y2, x3, y3);
            c = dist(x3, y3, x1, y1);

            // Calcula o semiperímetro
            p = (a + b + c) / 2.0;
            area = Math.sqrt(p * (p - a) * (p - b) * (p - c));

            System.out.printf(Locale.US, "%.3f%n", area);
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