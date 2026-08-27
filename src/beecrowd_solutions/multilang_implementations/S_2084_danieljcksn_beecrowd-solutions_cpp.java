import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();

        Integer nToken = scanner.nextInt();
        if (nToken == null) return;
        int n = nToken;

        Integer[] votos = new Integer[n];
        int total = 0;

        for (int i = 0; i < n; i++) {
            votos[i] = scanner.nextInt();
            total += votos[i];
        }

        // Ordena decrescentemente (requer array do tipo Integer em vez de int primitivo)
        Arrays.sort(votos, Collections.reverseOrder());

        double percent = 0.45 * total;

        // Caso o candidato mais votado possua pelo menos 45% de todos os votos
        if (votos[0] >= percent) {
            System.out.println("1");
        } else {
            percent = 0.40 * total;
            
            // Caso possua pelo menos 40% de todos os votos
            if (votos[0] >= percent) {
                boolean flag = true;
                
                for (int i = 1; i < n; i++) {
                    int dif = votos[0] - votos[i];
                    double pDif = ((double) dif / total) * 100;
                    
                    // Se a diferença para qualquer candidato for menor que 10%
                    if (pDif < 10) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    System.out.println("1");
                } else {
                    System.out.println("2");
                }
            } else {
                System.out.println("2");
            }
        }
    }

    // Leitor rápido de I/O otimizado para evitar Time Limit Exceeded (TLE)
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