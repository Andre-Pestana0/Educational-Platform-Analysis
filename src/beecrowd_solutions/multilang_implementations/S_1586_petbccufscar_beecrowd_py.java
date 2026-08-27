import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static int n;
    static String[] nomes;
    static long[] p;      // Pontuações
    static long[] sumP;   // Soma acumulada de p
    static long[] sumI;   // Soma acumulada de p[i] * i

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            
            line = line.trim();
            if (line.isEmpty()) continue;

            n = Integer.parseInt(line);
            if (n == 0) break;

            nomes = new String[n + 1];
            p = new long[n + 1];
            sumP = new long[n + 1];
            sumI = new long[n + 1];

            for (int i = 1; i <= n; i++) {
                nomes[i] = reader.readLine().trim();
                
                long pontuacao = 0;
                for (int j = 0; j < nomes[i].length(); j++) {
                    pontuacao += nomes[i].charAt(j);
                }

                p[i] = pontuacao;
                sumP[i] = sumP[i - 1] + p[i];
                sumI[i] = sumI[i - 1] + p[i] * i;
            }

            int r = buscaBinaria(1, n);

            if (r != -1) {
                output.append(nomes[r]).append("\n");
            } else {
                output.append("Impossibilidade de empate.\n");
            }
        }

        System.out.print(output);
    }

    // Calcula a diferença: Força(Time A) - Força(Time B)
    private static long calcularDiferenca(int k) {
        // Time A (integrantes de 1 a k):
        // Força = p[k]*1 + p[k-1]*2 + ... + p[1]*k
        // Equivale a: (k + 1) * sumP[k] - sumI[k]
        long tA = (k + 1L) * sumP[k] - sumI[k];

        // Time B (integrantes de k+1 a n):
        // Força = p[k+1]*1 + p[k+2]*2 + ... + p[n]*(n-k)
        // Equivale a: (sumI[n] - sumI[k]) - k * (sumP[n] - sumP[k])
        long sumP_B = sumP[n] - sumP[k];
        long sumI_B = sumI[n] - sumI[k];
        long tB = sumI_B - (long) k * sumP_B;

        return tA - tB;
    }

    private static int buscaBinaria(int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long diff = calcularDiferenca(mid);

            if (diff == 0) {
                return mid; // Achou o ponto de empate exato
            } else if (diff < 0) {
                low = mid + 1; // Time A está mais fraco, empurra o divisor para a direita
            } else {
                high = mid - 1; // Time A está mais forte, empurra o divisor para a esquerda
            }
        }

        return -1;
    }
}