import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static long kruskal(List<Edge> edges, int n) {
        long cost = 0;
        int[] tree_id = new int[n];
        List<Edge> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            tree_id[i] = i;
        }

        Collections.sort(edges);

        for (Edge e : edges) {
            if (tree_id[e.u] != tree_id[e.v]) {
                cost += e.weight;
                result.add(e);

                int old_id = tree_id[e.u];
                int new_id = tree_id[e.v];
                for (int i = 0; i < n; i++) {
                    if (tree_id[i] == old_id) {
                        tree_id[i] = new_id;
                    }
                }
            }
        }

        return cost;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();

        Integer r = scanner.nextInt();
        Integer c = scanner.nextInt();

        if (r == null || c == null) return;

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < c; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;
            int weight = scanner.nextInt();

            edges.add(new Edge(u, v, weight));
        }

        System.out.println(kruskal(edges, r));
    }

    // Classe auxiliar de I/O rápido
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