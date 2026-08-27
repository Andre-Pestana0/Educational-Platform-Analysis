import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final long MOD = 1000000007L;

    public static long modMul(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    public static long modSum(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }

    public static long pot(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = modMul(res, a);
            }
            a = modMul(a, a);
            b >>= 1;
        }
        return res % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Map<Character, Integer> dictionary = new HashMap<>();
        char digit = 'A';
        for (int i = 0; i <= 25; ++i, digit++) {
            dictionary.put(digit, i);
        }

        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String str = st.nextToken();
                int len = str.length() - 1;

                long total = 0;
                long potencia = 0;

                for (int i = len; i >= 0; --i) {
                    long res = modMul(dictionary.get(str.charAt(i)), pot(26, potencia));
                    total = modSum(total, res);
                    potencia++;
                }

                System.out.println(total % MOD);
            }
        }
    }
}