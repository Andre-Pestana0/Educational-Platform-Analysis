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

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int n = Integer.parseInt(st.nextToken());

            long[] pacote = new long[n];
            long[] prefsum = new long[n];

            for (int i = 0; i < n; i++) {
                while (!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                pacote[i] = Long.parseLong(st.nextToken());

                if (i == 0) {
                    prefsum[0] = pacote[0];
                } else {
                    prefsum[i] = pacote[i] + prefsum[i - 1];
                }
            }

            long m = -1;
            long l = 0;

            for (int i = 0; i < n; i++) {
                if (i == n - 1) break;

                long leftSum = prefsum[i];
                long rightSum = prefsum[n - 1] - prefsum[i];

                long currentMin = Math.min(leftSum, rightSum);

                if (currentMin > m) {
                    m = currentMin;
                    l = Math.max(leftSum, rightSum);
                }
            }

            System.out.println(m + " " + l);
        }
    }
}