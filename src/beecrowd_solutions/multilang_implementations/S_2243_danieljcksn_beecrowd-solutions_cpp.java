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

        int n = Integer.parseInt(line.trim());
        int[] heights = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[] fromLeft = new int[n];
        int[] fromRight = new int[n];

        fromLeft[0] = 1;
        fromRight[n - 1] = 1;

        // A maior altura possível para o triângulo, considerando apenas a parte esquerda
        for (int i = 1; i < n; i++) {
            fromLeft[i] = Math.min(fromLeft[i - 1] + 1, heights[i]);
        }

        // ... considerando apenas a parte direita
        for (int i = n - 2; i >= 0; i--) {
            fromRight[i] = Math.min(fromRight[i + 1] + 1, heights[i]);
        }

        // Encontrar o ponto em que o menor lado seja o máximo
        int ans = -1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, Math.min(fromLeft[i], fromRight[i]));
        }

        System.out.println(ans);
    }
}