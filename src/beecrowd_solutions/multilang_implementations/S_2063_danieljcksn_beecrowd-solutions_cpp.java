import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // O(log(min(a, b)))
    public static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }

    public static long lcmArray(List<Integer> numbers) {
        if (numbers.isEmpty()) return 0;
        if (numbers.size() == 1) return numbers.get(0);

        long mmc = lcm(numbers.get(0), numbers.get(1));

        for (int i = 2; i < numbers.size(); ++i) {
            mmc = lcm(mmc, numbers.get(i));
        }

        return mmc;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();

        Integer nToken = scanner.nextInt();
        if (nToken == null) return;
        int n = nToken;

        int[] holes = new int[n + 1];

        for (int i = 0; i < n; i++) {
            holes[i + 1] = scanner.nextInt();
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 1; i <= n; ++i) {
            int cont = 1;
            int k = i;

            while (holes[k] != i) {
                cont++;
                k = holes[k];
            }

            ans.add(cont);
        }

        System.out.println(lcmArray(ans));
    }

    // Leitor rápido de I/O otimizado
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