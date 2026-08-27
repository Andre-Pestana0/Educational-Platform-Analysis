import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Main {

    static class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x != o.x) return Integer.compare(this.x, o.x);
            return Integer.compare(this.y, o.y);
        }
    }

    // Produto vetorial para determinar a orientação (p -> q -> r)
    // > 0 : Curva à esquerda (anti-horário)
    // < 0 : Curva à direita (horário)
    // = 0 : Colinear
    private static long crossProduct(Point p, Point q, Point r) {
        return (long)(q.x - p.x) * (r.y - p.y) - (long)(q.y - p.y) * (r.x - p.x);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            List<Point> points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    String nextLine = br.readLine();
                    if (nextLine == null) break;
                    st = new StringTokenizer(nextLine);
                }
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points.add(new Point(x, y));
            }

            int camadas = 0;
            int pontosRestantes = n;
            boolean[] removido = new boolean[n];

            // Enquanto houver pelo menos 3 pontos não removidos
            while (pontosRestantes >= 3) {
                List<Integer> ativos = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if (!removido[i]) {
                        ativos.add(i);
                    }
                }

                if (ativos.size() < 3) break;

                // Ordena os pontos ativos por coordenada X (e desempata por Y)
                Collections.sort(ativos, (a, b) -> points.get(a).compareTo(points.get(b)));

                int numAtivos = ativos.size();
                int[] hull = new int[2 * numAtivos];
                int k = 0;

                // Parte inferior da envoltória (Lower Hull)
                for (int i = 0; i < numAtivos; i++) {
                    int idx = ativos.get(i);
                    while (k >= 2 && crossProduct(points.get(hull[k - 2]), points.get(hull[k - 1]), points.get(idx)) < 0) {
                        k--;
                    }
                    hull[k++] = idx;
                }

                // Parte superior da envoltória (Upper Hull)
                for (int i = numAtivos - 2, t = k + 1; i >= 0; i--) {
                    int idx = ativos.get(i);
                    while (k >= t && crossProduct(points.get(hull[k - 2]), points.get(hull[k - 1]), points.get(idx)) < 0) {
                        k--;
                    }
                    hull[k++] = idx;
                }

                // Se não conseguir formar um polígono válido
                if (k <= 3) break;

                // Marca os pontos da camada atual como removidos
                for (int i = 0; i < k - 1; i++) {
                    if (!removido[hull[i]]) {
                        removido[hull[i]] = true;
                        pontosRestantes--;
                    }
                }

                camadas++;
            }

            if (camadas % 2 != 0) {
                sb.append("Take this onion to the lab!\n");
            } else {
                sb.append("Do not take this onion to the lab!\n");
            }
        }

        System.out.print(sb.toString());
    }
}