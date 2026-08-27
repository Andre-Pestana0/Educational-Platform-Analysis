import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    // Classe auxiliar para representar uma Aresta do Grafo
    static class Aresta implements Comparable<Aresta> {
        int u, v, w;

        public Aresta(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        // Permite a ordenação das arestas pelo peso 'w'
        @Override
        public int compareTo(Aresta outra) {
            return Integer.compare(this.w, outra.w);
        }
    }

    static class Grafo {
        private List<Aresta> G; // Grafo (lista de arestas)
        private List<Aresta> T; // Árvore Geradora Mínima (MST)
        private int[] pai;

        public Grafo(int maxVertices) {
            // Instancia o array DSU considerando indices ate maxVertices
            pai = new int[maxVertices + 1];
            for (int i = 0; i <= maxVertices; i++) {
                pai[i] = i;
            }
            G = new ArrayList<>();
            T = new ArrayList<>();
        }

        public void adicionaAresta(int u, int v, int w) {
            G.add(new Aresta(u, v, w));
        }

        public int findSet(int i) {
            if (i == pai[i]) {
                return i;
            } else {
                // Compressao de caminho para otimizacao
                return pai[i] = findSet(pai[i]);
            }
        }

        public void unionSet(int u, int v) {
            pai[u] = pai[v];
        }

        public int kruskal() {
            int minST = 0;
            Collections.sort(G); // Ordena as arestas por peso crescente

            for (Aresta aresta : G) {
                int uRep = findSet(aresta.u);
                int vRep = findSet(aresta.v);

                if (uRep != vRep) {
                    T.add(aresta);
                    unionSet(uRep, vRep);
                }
            }

            for (Aresta aresta : T) {
                minST += aresta.w;
            }

            return minST;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (line == null || line.trim().isEmpty()) {
            return;
        }

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // Instancia o grafo (Garante tamanho suficiente para os IDs dos vértices)
        Grafo g = new Grafo(Math.max(n, m) + 1);

        for (int i = 0; i < m; i++) {
            while (st == null || !st.hasMoreTokens()) {
                line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }

            if (st != null && st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                g.adicionaAresta(x, y, c);
            }
        }

        int minST = g.kruskal();
        System.out.println(minST);
    }
}