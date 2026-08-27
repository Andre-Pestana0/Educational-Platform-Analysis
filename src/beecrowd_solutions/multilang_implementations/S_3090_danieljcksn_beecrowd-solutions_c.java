import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int comprimento = Integer.parseInt(st.nextToken());
        int largura = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int exercito1 = 0;
        int exercito2 = 0;

        /*
         O ângulo adjacente ao ângulo reto do triângulo retângulo correspondente à área superior do campo de batalha é:
         tan(x) = catetoOposto / catetoAdjacente
         arctan(tan(x)) = arctan(catetoOposto / catetoAdjacente)
         x = arctan(catetoOposto / catetoAdjacente)
        */
        double co = comprimento;
        double ca = largura;
        double angle = Math.atan(co / ca);

        /*
         A função Math.atan retorna o valor em radianos do ângulo. Para converter para graus, basta multiplicar por 180 e dividir por PI
        */
        angle = angle * 180.0 / Math.PI;

        while (s > 0) {
            st = ensureTokens(st, reader);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            co = x;
            ca = y;
            double angle1 = Math.atan(co / ca);
            angle1 = angle1 * 180.0 / Math.PI;

            if (angle1 < angle) {
                exercito1 += h;
            } else {
                exercito2 += h;
            }

            s--;
        }

        System.out.printf("%d %d%n", exercito1, exercito2);
    }

    private static StringTokenizer ensureTokens(StringTokenizer st, BufferedReader reader) throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) break;
            st = new StringTokenizer(line);
        }
        return st;
    }
}