import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder sb = new StringBuilder();

        String token = scanner.next();
        if (token == null) return;

        int n = Integer.parseInt(token);

        List<Integer> primos = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            Integer x = scanner.nextInt();
            if (x == null) break;

            if (isPrime(x)) {
                primos.add(x);
            }
        }

        // Gera o fatorial apenas para os números primos identificados
        for (int x : primos) {
            sb.append(x).append("! = ").append(factorial(x)).append("\n");
        }

        System.out.print(sb);
    }

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

        Integer nextInt() {
            String str = next();
            if (str == null) return null;
            return Integer.parseInt(str);
        }
    }
}