import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    // Retorna true se o reino A é atacado/conquistado pelo reino B
    private static boolean gotAttacked(int a, int b, int n) {
        if (a == 0 && b == n - 1) return true;
        return b + 1 == a;
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
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // Critério de parada: N=0, R=0, C=0, K=0
            if (n == 0 && r == 0 && c == 0 && k == 0) break;

            int[][] mtx = new int[r][c];

            // Leitura da matriz
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    while (st == null || !st.hasMoreTokens()) {
                        line = br.readLine();
                        st = new StringTokenizer(line);
                    }
                    mtx[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] ans = new int[r][c];

            // Simulação das K batalhas
            while (k-- > 0) {
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        int current = mtx[i][j];
                        boolean lost = false;

                        // Verifica os 4 vizinhos (Abaixo, Acima, Direita, Esquerda)
                        if (i + 1 < r && gotAttacked(current, mtx[i + 1][j], n)) {
                            ans[i][j] = mtx[i + 1][j];
                            lost = true;
                        } else if (i - 1 >= 0 && gotAttacked(current, mtx[i - 1][j], n)) {
                            ans[i][j] = mtx[i - 1][j];
                            lost = true;
                        } else if (j + 1 < c && gotAttacked(current, mtx[i][j + 1], n)) {
                            ans[i][j] = mtx[i][j + 1];
                            lost = true;
                        } else if (j - 1 >= 0 && gotAttacked(current, mtx[i][j - 1], n)) {
                            ans[i][j] = mtx[i][j - 1];
                            lost = true;
                        }

                        if (!lost) {
                            ans[i][j] = mtx[i][j];
                        }
                    }
                }

                // Troca as referências das matrizes de forma eficiente
                int[][] temp = mtx;
                mtx = ans;
                ans = temp;
            }

            // Impressão do resultado
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    sb.append(mtx[i][j]);
                    if (j != c - 1) {
                        sb.append(" ");
                    }
                }
                sb.append("\n");
            }
            System.out.print(sb.toString());
        }
    }
}