import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static class Book {
        int weight;
        int interest;

        Book(int weight, int interest) {
            this.weight = weight;
            this.interest = interest;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int instance = 1;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int n = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (n == 0) break;

            Book[] books = new Book[n];

            for (int i = 0; i < n; i++) {
                while (!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                int weight = Integer.parseInt(st.nextToken());
                int interest = Integer.parseInt(st.nextToken());
                books[i] = new Book(weight, interest);
            }

            int[][] dp = new int[n + 1][c + 1];

            for (int b = 1; b <= n; b++) {
                for (int w = 1; w <= c; w++) {
                    if (w < books[b - 1].weight) {
                        dp[b][w] = dp[b - 1][w];
                    } else {
                        dp[b][w] = Math.max(
                            dp[b - 1][w],
                            books[b - 1].interest + dp[b - 1][w - books[b - 1].weight]
                        );
                    }
                }
            }

            System.out.println("Caso " + (instance++) + ": " + dp[n][c]);
        }
    }
}