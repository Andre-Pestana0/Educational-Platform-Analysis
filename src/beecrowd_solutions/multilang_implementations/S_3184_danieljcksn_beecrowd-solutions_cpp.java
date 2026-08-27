import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    // -2 = parede (#), -1 = armadilha (T), 0 = caminho possível (.), 1 = visitado/seguro (P)
    static void f(int[][] ans, int i, int j) {
        if (ans[i][j] == -2)
            return;

        // Se esta posição for segura, verifica se há armadilhas nas 4 posições vizinhas
        if (ans[i][j] == 1) {
            if (ans[i - 1][j] == -1 || 
                ans[i + 1][j] == -1 || 
                ans[i][j - 1] == -1 || 
                ans[i][j + 1] == -1) {
                return;
            }

            if (ans[i - 1][j] == 0) {
                ans[i - 1][j] = 1;
                f(ans, i - 1, j);
            }

            if (ans[i + 1][j] == 0) {
                ans[i + 1][j] = 1;
                f(ans, i + 1, j);
            }

            if (ans[i][j - 1] == 0) {
                ans[i][j - 1] = 1;
                f(ans, i, j - 1);
            }

            if (ans[i][j + 1] == 0) {
                ans[i][j + 1] = 1;
                f(ans, i, j + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        char[][] m = new char[h][w];
        int[][] ans = new int[h][w];

        int playerI = 0;
        int playerJ = 0;

        for (int i = 0; i < h; ++i) {
            line = reader.readLine();
            while (line != null && line.trim().isEmpty()) {
                line = reader.readLine();
            }

            for (int j = 0; j < w; ++j) {
                m[i][j] = line.charAt(j);
                if (m[i][j] == 'P') {
                    playerI = i;
                    playerJ = j;
                    ans[i][j] = 1;
                } else if (m[i][j] == '.' || m[i][j] == 'G') {
                    ans[i][j] = 0;
                } else if (m[i][j] == '#') {
                    ans[i][j] = -2;
                } else {
                    ans[i][j] = -1;
                }
            }
        }

        f(ans, playerI, playerJ);

        int gold = 0;
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                if (m[i][j] == 'G' && ans[i][j] == 1) {
                    gold++;
                }
            }
        }

        System.out.println(gold);
    }
}