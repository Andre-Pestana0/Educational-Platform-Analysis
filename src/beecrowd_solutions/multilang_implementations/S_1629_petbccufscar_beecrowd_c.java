import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int casos = Integer.parseInt(st.nextToken());
            if (casos == 0) break;

            while (casos > 0) {
                // Lê a próxima palavra/string, lidando com linhas em branco ou múltiplos tokens
                if (!st.hasMoreTokens()) {
                    line = reader.readLine();
                    if (line == null) break;
                    st = new StringTokenizer(line);
                    if (!st.hasMoreTokens()) continue;
                }

                String s = st.nextToken();
                int tamanho = s.length();

                int uns = 0;
                int zeros = 0;

                // Soma os dígitos nas posições pares (índices 0, 2, 4...)
                for (int i = 0; i < tamanho; i += 2) {
                    zeros += (s.charAt(i) - '0');
                }

                // Soma os dígitos nas posições ímpares (índices 1, 3, 5...)
                for (int i = 1; i < tamanho; i += 2) {
                    uns += (s.charAt(i) - '0');
                }

                zeros = somaDigitos(zeros);
                uns = somaDigitos(uns);

                output.append(zeros + uns).append("\n");
                casos--;
            }
        }

        System.out.print(output);
    }

    public static int somaDigitos(int n) {
        int soma = 0;

        while (n > 0) {
            soma += (n % 10);
            n /= 10;
        }
        return soma;
    }
}