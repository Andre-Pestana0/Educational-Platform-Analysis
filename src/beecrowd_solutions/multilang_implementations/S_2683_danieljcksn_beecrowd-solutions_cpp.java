import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    // Representação de uma aresta
    static class Edge {
        int src, dest, weight;
    }

    // Representação do Grafo
    static class Graph {
        int V, E;
        Edge[] edge;

        Graph(int V, int E) {
            this.V = V;
            this.E = E;
            edge = new Edge[E];
            for (int i = 0; i < E; i++) {
                edge[i] = new Edge();
            }
        }
    }

    // Estrutura para Disjoint Set (Union-Find)
    static class Subset {
        int parent;
        int rank;
    }

    // Busca o representante do elemento (com compressão de caminho)
    public static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    // Une dois conjuntos (com união por rank)
    public static void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // Executa o Algoritmo de Kruskal (Mínimo ou Máximo a depender da ordenação prévia)
    public static int kruskalMST(Graph graph, int maxVertexIndex) {
        int totalWeight = 0;
        int numVertices = maxVertexIndex + 1;

        Edge[] result = new Edge[numVertices];
        int e = 0;
        int i = 0;

        Subset[] subsets = new Subset[numVertices];
        for (int v = 0; v < numVertices; ++v) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        while (e < numVertices - 1 && i < graph.E) {
            Edge nextEdge = graph.edge[i++];

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }
        }

        for (i = 0; i < e; ++i) {
            totalWeight += result[i].weight;
        }

        return totalWeight;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (line == null || line.trim().isEmpty()) {
            return;
        }

        int n = Integer.parseInt(line.trim());
        Graph graph = new Graph(n, n);

        int maxVertexIndex = 0;
        StringTokenizer st = null;

        for (int i = 0; i < n; i++) {
            while (st == null || !st.hasMoreTokens()) {
                line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }

            if (st != null && st.hasMoreTokens()) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                graph.edge[i].src = u;
                graph.edge[i].dest = v;
                graph.edge[i].weight = w;

                maxVertexIndex = Math.max(maxVertexIndex, Math.max(u, v));
            }
        }

        // 1. Ordenação crescente (para obter o custo da Árvore Geradora Mínima)
        Arrays.sort(graph.edge, (a, b) -> Integer.compare(a.weight, b.weight));
        int minST = kruskalMST(graph, maxVertexIndex);

        // 2. Ordenação decrescente (para obter o custo da Árvore Geradora Máxima)
        Arrays.sort(graph.edge, (a, b) -> Integer.compare(b.weight, a.weight));
        int maxST = kruskalMST(graph, maxVertexIndex);

        // Imprime o valor máximo primeiro e depois o mínimo, conforme o problema exige
        System.out.println(maxST);
        System.out.println(minST);
    }
}