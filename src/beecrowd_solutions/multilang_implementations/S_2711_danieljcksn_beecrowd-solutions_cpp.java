import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int powmod(int a, int b, int m) {
        long res = 1;
        long base = a;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * base) % m;
            }
            base = (base * base) % m;
            b >>= 1;
        }
        return (int) res;
    }

    // Retorna o menor x para o qual a^x % m = b % m, onde a e m sao coprimos.
    static int solve(int a, int b, int m) {
        a %= m;
        b %= m;
        int n = (int) Math.sqrt(m) + 1;

        long an = 1;
        long baseA = a;
        for (int i = 0; i < n; ++i) {
            an = (an * baseA) % m;
        }

        Map<Integer, Integer> vals = new HashMap<>();
        long cur = b;
        for (int q = 0; q <= n; ++q) {
            vals.put((int) cur, q);
            cur = (cur * baseA) % m;
        }

        long curAn = 1;
        for (int p = 1; p <= n; ++p) {
            curAn = (curAn * an) % m;
            if (vals.containsKey((int) curAn)) {
                int ans = n * p - vals.get((int) curAn);
                return ans;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (b % m == 1) {
                System.out.println(0);
            } else {
                System.out.println(solve(a, b, m));
            }
        }
    }
}