import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        while (true) {
            String aa = scanner.next();
            String bb = scanner.next();

            if (aa == null || bb == null) break;
            if (aa.equals("0") && bb.equals("0")) break;

            BigInteger a = new BigInteger(aa);
            BigInteger b = new BigInteger(bb);

            BigInteger f = BigInteger.ONE;
            BigInteger s = BigInteger.valueOf(2);

            long ans = 0;

            // f >= a && f <= b  -->  f.compareTo(a) >= 0 && f.compareTo(b) <= 0
            if (f.compareTo(a) >= 0 && f.compareTo(b) <= 0) {
                ans++;
            }
            if (s.compareTo(a) >= 0 && s.compareTo(b) <= 0) {
                ans++;
            }

            // Enquanto s <= b  -->  s.compareTo(b) <= 0
            while (s.compareTo(b) <= 0) {
                BigInteger cs = s;
                s = f.add(s);
                f = cs;

                if (s.compareTo(a) >= 0 && s.compareTo(b) <= 0) {
                    ans++;
                }
            }

            output.append(ans).append('\n');
        }

        System.out.print(output);
    }

    // Leitor rápido de I/O otimizado para Maratona/Beecrowd/Codeforces
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }
    }
}