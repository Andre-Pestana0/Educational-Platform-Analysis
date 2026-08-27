import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;

public class Main {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double dist(Point A, Point B) {
        return Math.sqrt(Math.pow(B.x - A.x, 2) + Math.pow(B.y - A.y, 2));
    }

    // Para determinar a orientação da tripla (p, q, r):
    // 0 --> p, q e r são colineares 
    // 1 --> Sentido Horário 
    // 2 --> Sentido Anti-horário
    public static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0;  // colineares
        return (val > 0) ? 1 : 2; // horário ou anti-horário
    }

    // Algoritmo de Jarvis
    public static void convexHull(List<Point> points, int n, Deque<Point> S) {
        if (n < 3) return;

        List<Point> hull = new ArrayList<>();

        // Encontra o ponto mais à esquerda (menor X)
        int l = 0;
        for (int i = 1; i < n; i++) {
            if (points.get(i).x < points.get(l).x) {
                l = i;
            }
        }

        int p = l, q;
        do {
            hull.add(points.get(p));

            q = (p + 1) % n;

            for (int i = 0; i < n; i++) {
                if (orientation(points.get(p), points.get(i), points.get(q)) == 2) {
                    q = i;
                }
            }

            p = q;

        } while (p != l);

        // Transfere o resultado para a pilha S
        for (Point pt : hull) {
            S.push(pt);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        
        while (true) {
            Integer nToken = scanner.nextInt();
            if (nToken == null || nToken == 0) break;

            int n = nToken;
            List<Point> pontos = new ArrayList<>();
            Deque<Point> hull = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                pontos.add(new Point(x, y));
            }

            convexHull(pontos, pontos.size(), hull);

            double distancia = 0;
            Point P = hull.pop();
            Point primeiro = P;

            // Calcula a distância entre os pares de computadores da envoltória
            while (!hull.isEmpty()) {
                distancia += dist(P, hull.peek());
                P = hull.pop();
            }

            // Fecha o ciclo somando a distância de volta ao primeiro elemento
            distancia += dist(primeiro, P);

            // Formatação com ponto como separador decimal para o Beecrowd
            System.out.printf(Locale.US, "Tera que comprar uma fita de tamanho %.2f.\n", distancia);
        }
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