import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (line == null || line.trim().isEmpty()) {
            return;
        }

        StringTokenizer st = new StringTokenizer(line);
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        char[][] map = new char[m][n];

        for (int i = 0; i < m; ++i) {
            String row = br.readLine();
            for (int j = 0; j < n; ++j) {
                map[i][j] = row.charAt(j);
            }
        }

        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (map[i][j] != '#') continue;

                // Células nas bordas do mapa
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    ans++;
                    continue;
                }

                // Células com pelo menos um vizinho contendo '.'
                if (map[i - 1][j] == '.' || map[i + 1][j] == '.' || 
                    map[i][j - 1] == '.' || map[i][j + 1] == '.') {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}