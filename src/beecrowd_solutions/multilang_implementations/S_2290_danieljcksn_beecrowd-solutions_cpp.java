import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            long n = Long.parseLong(st.nextToken());
            if (n == 0) break;

            long[] a = new long[(int) n];

            // Lê os N elementos tratando possíveis quebras de linha na entrada
            for (int i = 0; i < n; i++) {
                while (!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                a[i] = Long.parseLong(st.nextToken());
            }

            // Equivale ao std::sort
            Arrays.sort(a);

            // Simulação da estrutura pair<ll, ll> last (valor, quantidade)
            long lastVal = a[0];
            long lastCount = 1;

            long firstAns = lastVal;
            long secondAns = 0;
            boolean flag = false;

            for (int i = 1; i < n; i++) {
                if (a[i] == lastVal) {
                    lastCount++;
                } else {
                    if ((lastCount & 1) != 0) { // Teste de número ímpar (último bit ativo)
                        if (!flag) {
                            firstAns = lastVal;
                            flag = true;

                            lastVal = a[i];
                            lastCount = 1;
                        } else {
                            secondAns = lastVal;
                        }
                    } else {
                        lastVal = a[i];
                        lastCount = 1;
                    }
                }
            }

            if ((lastCount & 1) != 0) {
                secondAns = lastVal;
            }

            System.out.println(firstAns + " " + secondAns);
        }
    }
}