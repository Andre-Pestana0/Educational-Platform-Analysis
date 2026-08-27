import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        while (true) {
            Integer lToken = scanner.nextInt();
            Integer cToken = scanner.nextInt();
            Integer pToken = scanner.nextInt();

            if (lToken == null || cToken == null || pToken == null) break;

            int l = lToken;
            int c = cToken;
            int p = pToken;

            // Caso de parada: todos os valores iguais a zero
            if (l == 0 && c == 0 && p == 0) break;

            int[][] matriz = new int[l][c];
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < c; j++) {
                    matriz[i][j] = scanner.nextInt();
                }
            }

            boolean flag = false;

            for (int i = 0; i < l; i++) {
                int vEsq = 0, pEsq = -1;
                int vDir = 0, pDir = -1;

                // Encontra o ventilador mais próximo à esquerda do balão
                for (int j = p - 2; j >= 0; j--) {
                    if (matriz[i][j] != 0) {
                        vEsq = matriz[i][j];
                        pEsq = j;
                        break;
                    }
                }

                // Encontra o ventilador mais próximo à direita do balão
                for (int j = p; j < c; j++) {
                    if (matriz[i][j] != 0) {
                        vDir = matriz[i][j];
                        pDir = j;
                        break;
                    }
                }

                // Calcula a diferença de potência entre os ventiladores
                int dif = Math.abs(vDir - vEsq);

                if (vDir > vEsq) {
                    p -= dif; // Empurra o balão para a esquerda
                } else if (vDir < vEsq) {
                    p += dif; // Empurra o balão para a direita
                }

                // Verifica se o balão atingiu ou ultrapassou um ventilador
                if (p - 1 <= pEsq) {
                    output.append("BOOM ").append(i + 1).append(" ").append(pEsq + 1).append("\n");
                    flag = true;
                    break;
                } else if (p - 1 >= pDir) {
                    output.append("BOOM ").append(i + 1).append(" ").append(pDir + 1).append("\n");
                    flag = true;
                    break;
                }
            }

            // Caso o balão tenha saído sem estourar
            if (!flag) {
                output.append("OUT ").append(p).append("\n");
            }
        }

        System.out.print(output);
    }

    // Leitor rápido de I/O otimizado
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            String s = next();
            if (s == null) return null;
            return Integer.parseInt(s);
        }
    }
}