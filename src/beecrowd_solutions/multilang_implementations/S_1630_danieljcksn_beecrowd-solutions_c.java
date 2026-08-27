import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    // Função para calcular o Máximo Divisor Comum (MDC)
    public static int mdc(int x, int y) {
        int resto = x % y;

        while (resto != 0) {
            x = y;
            y = resto;
            resto = x % y;
        }

        return y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        String line;

        // Trata o EOF (End of File)
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // Em um terreno quadrado, 4 estacas bastam
            if (x == y) {
                output.append("4\n");
            } else {
                // Garante que X seja menor ou igual a Y para o algoritmo do MDC
                if (x > y) {
                    int aux = x;
                    x = y;
                    y = aux;
                }

                int divisorcomum = mdc(x, y);
                int totalEstacas = 2 * (x / divisorcomum) + 2 * (y / divisorcomum);
                output.append(totalEstacas).append("\n");
            }
        }

        System.out.print(output);
    }
}