import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int caso = 1;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int r = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            // Critério de parada: R = 0 e N = 0
            if (r == 0 && n == 0) break;

            // Caso o número de ruas seja maior que o limite máximo (N sem sufixo + 26*N com letras)
            if (r > 27 * n) {
                System.out.printf("Case %d: impossible\n", caso);
            } else if (r <= n) {
                // Se cabem todas apenas com números
                System.out.printf("Case %d: 0\n", caso);
            } else {
                // Cálculo direto do número de letras (sufixos) necessárias
                int sufixos = (r - 1) / n;
                System.out.printf("Case %d: %d\n", caso, sufixos);
            }

            caso++;
        }
    }
}