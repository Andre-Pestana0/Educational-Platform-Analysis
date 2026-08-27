import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    private static int getDigit(char a) {
        return (int) (a - '0');
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();

        String A = scanner.next();
        if (A == null) return;

        int B = scanner.nextInt();

        long ans = 0;

        for (int i = 0; i < A.length(); i++) {
            ans = (ans * 10 + getDigit(A.charAt(i))) % B;
        }

        System.out.println(ans);
    }

    // Leitor rápido de entrada
    static class FastScanner {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreElements()) {
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}