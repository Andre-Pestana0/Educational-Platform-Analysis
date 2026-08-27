import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    // Função para calcular o MDC usando o algoritmo de Euclides
    public static int calculaMdc(int a, int b) {
        int resto = a % b;

        while (resto != 0) {
            a = b;
            b = resto;
            resto = a % b;
        }

        return b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            // Garante que x seja a medida da maior hipotenusa
            if (x < y) {
                int aux = x;
                x = y;
                y = aux;
            }
            if (x < z) {
                int aux = x;
                x = z;
                z = aux;
            }

            // Eleva ao quadrado com multiplicação inteira (evita pow/double)
            int a = x * x;
            int b = y * y;
            int c = z * z;

            // Verifica o Teorema de Pitágoras
            if (a == (b + c)) {
                // mdc(x, y, z) = mdc(mdc(x, y), z)
                int mdc = calculaMdc(x, y);
                mdc = calculaMdc(mdc, z);

                if (mdc == 1) {
                    sb.append("tripla pitagorica primitiva\n");
                } else {
                    sb.append("tripla pitagorica\n");
                }
            } else {
                sb.append("tripla\n");
            }
        }

        System.out.print(sb.toString());
    }
}