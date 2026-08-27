import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        Integer cTokens = scanner.nextInt();
        if (cTokens == null) return;
        int c = cTokens;

        while (c-- > 0) {
            int p = scanner.nextInt();

            int pR = 0;
            int pB = 0;
            int pG = 0;

            while (p-- > 0) {
                char a = scanner.next().charAt(0);
                char b = scanner.next().charAt(0);

                if (a == 'B') {
                    if (b == 'R')
                        pB += 2;
                    else
                        pB++;
                } else if (a == 'R') {
                    if (b == 'G')
                        pR += 2;
                    else
                        pR++;
                } else {
                    if (b == 'B')
                        pG += 2;
                    else
                        pG++;
                }
            }

            if (pB == pR && pB == pG) {
                output.append("trempate\n");
            } else if (pB > pR && pB > pG) {
                output.append("blue\n");
            } else if (pG > pR && pG > pB) {
                output.append("green\n");
            } else if (pR > pG && pR > pB) {
                output.append("red\n");
            } else {
                output.append("empate\n");
            }
        }

        System.out.print(output);
    }

    // Leitor rápido para substituir o cin/cin.tie(NULL) do C++
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

        Integer nextInt() {
            String s = next();
            if (s == null) return null;
            return Integer.parseInt(s);
        }
    }
}