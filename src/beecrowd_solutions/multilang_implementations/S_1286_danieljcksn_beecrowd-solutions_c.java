import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            int n = Integer.parseInt(line);
            if (n == 0) break;

            int maxPizzas = Integer.parseInt(br.readLine().trim());

            int[] tempoTotal = new int[n];
            int[] qtPizzas = new int[n];

            for (int i = 0; i < n; i++) {
                String lineData = br.readLine();
                while (lineData == null || lineData.trim().isEmpty()) {
                    lineData = br.readLine();
                }
                
                StringTokenizer st = new StringTokenizer(lineData);
                tempoTotal[i] = Integer.parseInt(st.nextToken());
                qtPizzas[i] = Integer.parseInt(st.nextToken());
            }

            // Programação Dinâmica (Mochila Binária 0/1)
            int[][] dp = new int[n + 1][maxPizzas + 1];

            for (int i = 1; i <= n; i++) {
                int tempo = tempoTotal[i - 1];
                int pizzas = qtPizzas[i - 1];

                for (int w = 1; w <= maxPizzas; w++) {
                    if (pizzas > w) {
                        dp[i][w] = dp[i - 1][w];
                    } else {
                        dp[i][w] = Math.max(dp[i - 1][w], tempo + dp[i - 1][w - pizzas]);
                    }
                }
            }

            System.out.println(dp[n][maxPizzas] + " min.");
        }
    }
}