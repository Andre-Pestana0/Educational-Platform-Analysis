import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class Main {

    // Retorna a lista de números primos (incluindo o 1 conforme o código original)
    public static List<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        primes.add(1);

        for (int p = 2; p <= n; p++) {
            if (prime[p]) {
                primes.add(p);
            }
        }

        return primes;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        // Pré-processamento dos primos
        List<Integer> primes = sieveOfEratosthenes(2000000);

        Integer tTokens = scanner.nextInt();
        if (tTokens == null) return;
        int t = tTokens;

        while (t-- > 0) {
            int n = scanner.nextInt();
            Set<Integer> arr = new HashSet<>();

            for (int i = 0; i < n; i++) {
                arr.add(scanner.nextInt());
            }

            for (int i : primes) {
                if (!arr.contains(i)) {
                    output.append(i - 1).append('\n');
                    break;
                }
            }
        }

        System.out.print(output);
    }

    // Leitor rápido para substituir cin/cin.tie(NULL)
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