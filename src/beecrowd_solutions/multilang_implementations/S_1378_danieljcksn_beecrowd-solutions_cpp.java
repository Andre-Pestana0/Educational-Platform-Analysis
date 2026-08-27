import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Main {

    // Classe auxiliar para representar um ponto 2D
    static class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    // Retorna a distância ao quadrado entre dois pontos
    private static long squaredDist(Point a, Point b) {
        long dx = b.x - a.x;
        long dy = b.y - a.y;
        return (dx * dx) + (dy * dy);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            Point[] p = new Point[n];
            for (int i = 0; i < n; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    String nextLine = br.readLine();
                    if (nextLine == null) break;
                    st = new StringTokenizer(nextLine);
                }
                long x = Long.parseLong(st.nextToken());
                long y = Long.parseLong(st.nextToken());
                p[i] = new Point(x, y);
            }

            long ans = 0;
            HashMap<Long, Long> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                map.clear();
                for (int j = 0; j < n; j++) {
                    if (i == j) continue; // Pula a distância de um ponto para ele mesmo (dist=0)

                    long dist = squaredDist(p[i], p[j]);
                    map.put(dist, map.getOrDefault(dist, 0L) + 1L);
                }

                // Para cada distância que se repete 'k' vezes a partir do vertice 'i',
                // formamos C(k, 2) = k * (k - 1) / 2 triângulos isósceles com vértice principal em 'i'.
                for (long k : map.values()) {
                    ans += (k * (k - 1)) / 2;
                }
            }

            System.out.println(ans);
        }
    }
}