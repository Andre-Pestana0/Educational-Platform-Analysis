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
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int trem = 0;
        boolean flag = true;

        for (int i = 0; i < n; ++i) {
            st = ensureTokens(st, reader);
            int sairam = Integer.parseInt(st.nextToken());
            int entraram = Integer.parseInt(st.nextToken());
            int ficaram = Integer.parseInt(st.nextToken());

            // Se saírem mais pessoas do que a quantidade atual do trem
            if (sairam > trem) {
                flag = false;
            }
            trem -= sairam;

            // Se o trem exceder a capacidade máxima
            if ((entraram + trem) > c) {
                flag = false;
            }
            trem += entraram;

            // Os passageiros devem esperar apenas se o trem estiver em sua capacidade máxima
            if (ficaram > 0) {
                if (trem != c) {
                    flag = false;
                }
            }
        }

        // Verifica se o trem está vazio na última estação e se todas as regras foram cumpridas
        if (flag && trem == 0) {
            System.out.println("possible");
        } else {
            System.out.println("impossible");
        }
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