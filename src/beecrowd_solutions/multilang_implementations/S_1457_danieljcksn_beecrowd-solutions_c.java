import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    // Função para calcular o K-fatorial
    public static long kFatorial(int n, int k) {
        long result = n;
        int i = 1;

        while (true) {
            if ((n - i * k) < 1) {
                break;
            }

            result *= (n - i * k);
            i++;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String line = br.readLine();
        if (line == null) return;
        
        int t = Integer.parseInt(line.trim());

        while (t-- > 0) {
            String nStr = br.readLine().trim();

            int k = 0;
            int i = nStr.length() - 1;

            // Conta a quantidade de pontos de exclamação (K) ao final da string
            while (i >= 0 && nStr.charAt(i) == '!') {
                k++;
                i--;
            }

            // Extrai a parte numérica de N (do início até o último dígito)
            int n = Integer.parseInt(nStr.substring(0, i + 1));

            // Calcula e imprime o K-fatorial
            long result = kFatorial(n, k);
            System.out.println(result);
        }
    }
}