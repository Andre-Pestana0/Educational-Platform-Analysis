import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());

        // TreeMap garante a ordenação das chaves em ordem alfabética (igual ao std::map do C++)
        Map<String, Integer> P = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            st = ensureTokens(st, reader);
            String p = st.nextToken();
            int c = Integer.parseInt(st.nextToken());
            P.put(p, c);
        }

        // Leitura continuada até o final do arquivo (EOF)
        while ((st = getNextTokens(st, reader)) != null) {
            String p1 = st.nextToken();
            String p2 = st.nextToken();
            int q = Integer.parseInt(st.nextToken());

            int custoP2 = P.get(p2);

            if (P.containsKey(p1)) {
                P.put(p1, P.get(p1) + (custoP2 * q));
            } else {
                P.put(p1, custoP2 * q);
            }
        }

        int i = 0;
        for (Map.Entry<String, Integer> entry : P.entrySet()) {
            i++;
            // Garantindo que sejam impressas apenas as peças compostas
            if (i <= n) {
                continue;
            } else {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
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

    private static StringTokenizer getNextTokens(StringTokenizer st, BufferedReader reader) throws IOException {
        if (st != null && st.hasMoreTokens()) {
            return st;
        }
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                return new StringTokenizer(line);
            }
        }
        return null;
    }
}