import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while (true) {
            while (st == null || !st.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) return;
                st = new StringTokenizer(line);
            }

            int n = Integer.parseInt(st.nextToken());
            if (n == -1) {
                break;
            }

            int b = n % 257;
            int c = n % 193;

            float delta = (float) (Math.pow(b, 2) - 4 * c);

            if (delta < 0) {
                System.out.println("So o ouro");
            } else {
                // Calculando as raízes
                float x1 = (float) ((-b + Math.sqrt(delta)) / 2.0);
                float x2 = (float) ((-b - Math.sqrt(delta)) / 2.0);

                // Em C, (!x1) equivale a (x1 == 0). Em Java é necessário ser explícito.
                boolean x1Zero = (x1 == 0);
                boolean x2Zero = (x2 == 0);

                // Não há raízes reais positivas (ou válidas segundo a regra do problema)
                if (x1 < 0 && x2 < 0) {
                    System.out.println("So o ouro");
                } 
                // Há apenas uma raiz real
                else if ((x1 < 0 && x2Zero) || (x2 < 0 && x1Zero) || (x1Zero && x2Zero)) {
                    System.out.println("Bom");
                } 
                // Há duas ou mais raízes
                else {
                    System.out.println("Regular");
                }
            }
        }
    }
}