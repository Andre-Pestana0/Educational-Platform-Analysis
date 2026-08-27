import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Pré-computação do vetor
        long[] ans = new long[41];
        ans[0] = 1;
        ans[1] = 1;

        for (int i = 2; i <= 40; i++) {
            ans[i] = ans[i - 1] + ans[i - 2];
        }

        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            System.out.println(ans[n]);
        }
    }
}