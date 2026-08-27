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

        // Em Java, arrays de booleanos já iniciam preenchidos com 'false' por padrão
        boolean[][] coord = new boolean[501][501];
        boolean result = false;

        StringTokenizer st = null;

        for (int i = 0; i < n; i++) {
            while (st == null || !st.hasMoreTokens()) {
                line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }

            if (st != null && st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                // Caso o raio já tenha caído nessa coordenada, marca o resultado e interrompe
                if (coord[x][y]) {
                    result = true;
                    break;
                }

                // Caso contrário, registra a ocorrência do raio
                coord[x][y] = true;
            }
        }

        // Imprime 1 se houve raio no mesmo lugar, caso contrário 0
        System.out.println(result ? 1 : 0);
    }
}