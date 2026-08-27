import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 999999;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int conexoes = Integer.parseInt(st.nextToken());
        int perguntas = Integer.parseInt(st.nextToken());

        int[][] matrizAdj = new int[n + 1][n + 1];

        // Inicializa todas as distâncias com INF
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                matrizAdj[i][j] = INF;
            }
        }

        // Leitura das conexões do grafo
        while (conexoes-- > 0) {
            st = ensureTokens(st, reader);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            matrizAdj[a][b] = 1;
            matrizAdj[b][a] = 1;
        }

        // Algoritmo de Floyd-Warshall
        for (int k = 0; k <= n; k++) {
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    if (matrizAdj[i][k] + matrizAdj[k][j] < matrizAdj[i][j]) {
                        matrizAdj[i][j] = matrizAdj[i][k] + matrizAdj[k][j];
                    }
                }
            }
        }

        // Leitura e resposta das consultas
        StringBuilder sb = new StringBuilder();
        while (perguntas-- > 0) {
            st = ensureTokens(st, reader);
            int k = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            if (matrizAdj[k][l] == INF || matrizAdj[l][k] == INF) {
                sb.append("Deu ruim\n");
            } else {
                sb.append("Lets que lets\n");
            }
        }

        System.out.print(sb.toString());
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