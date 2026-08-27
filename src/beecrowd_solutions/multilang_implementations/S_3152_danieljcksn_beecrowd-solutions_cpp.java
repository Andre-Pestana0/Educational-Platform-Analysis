import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final double EPS = 1e-9;

    static class Pair implements Comparable<Pair> {
        double a; // representa 'first' (coordenada x)
        double b; // representa 'second' (coordenada y)

        public Pair(double a, double b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair other) {
            if (Double.compare(this.a, other.a) != 0) {
                return Double.compare(this.a, other.a);
            }
            return Double.compare(this.b, other.b);
        }
    }

    // Calculo da área do quadrilátero através das coordenadas
    static double area(List<Pair> arr) {
        double val1 = arr.get(0).a * arr.get(1).b + arr.get(1).a * arr.get(2).b + arr.get(2).a * arr.get(3).b + arr.get(3).a * arr.get(0).b;
        double val2 = arr.get(1).a * arr.get(0).b + arr.get(2).a * arr.get(1).b + arr.get(3).a * arr.get(2).b + arr.get(0).a * arr.get(3).b;
        
        return Math.abs(0.5 * (val1 - val2));
    }

    static double dist(Pair p1, Pair p2) {
        return Math.sqrt((p1.a - p2.a) * (p1.a - p2.a) + (p1.b - p2.b) * (p1.b - p2.b));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);

        List<Pair> ta = new ArrayList<>();
        List<Pair> tb = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            st = ensureTokens(st, reader);
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            ta.add(new Pair(x, y));
        }

        for (int i = 0; i < 4; i++) {
            st = ensureTokens(st, reader);
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            tb.add(new Pair(x, y));
        }

        Collections.sort(ta);
        Collections.sort(tb);

        double areaA = area(ta);
        double areaB = area(tb);

        if (Math.abs(areaA - areaB) < EPS || areaB > areaA) {
            System.out.println("terreno B");
        } else {
            System.out.println("terreno A");
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