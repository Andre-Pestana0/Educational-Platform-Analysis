import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static final int MAX = 10000000;

    public static int cont(int n, int p) {
        int cont = 0;
        long pot = p;

        while ((n / pot) > 0) {
            cont += n / pot;
            pot *= p; // Evita overflow mantendo 'pot' como long
        }

        return cont;
    }

    // https://cp-algorithms.com/algebra/factorial-modulo.html#toc-tgt-2
    public static boolean isEven(int n, int k) {
        if (cont(n + k - 1, 2) > cont(k, 2) + cont(n - 1, 2)) {
            return true;
        }
        return false;
    }

    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);

        prime[0] = false;
        prime[1] = false;

        for (int p = 2; (long) p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }

        return prime;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();

        boolean[] primeNumbers = sieveOfEratosthenes(MAX);

        Integer aToken = scanner.nextInt();
        Integer bToken = scanner.nextInt();

        if (aToken == null || bToken == null) return;

        int a = aToken;
        int b = bToken;

        // Garantindo que a <= b
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        // Número de primos no intervalo [a, b]
        int countPrimes = 0;
        for (int i = a; i <= b; ++i) {
            if (primeNumbers[i]) {
                countPrimes++;
            }
        }

        // Número de fatores que devem ser tomados
        int numFactors = b - a;

        if (a == b) {
            System.out.println("?");
        } else {
            if (countPrimes == 0) {
                System.out.println("Bob");
            } else {
                if (isEven(countPrimes, numFactors)) {
                    System.out.println("Bob");
                } else {
                    System.out.println("Alice");
                }
            }
        }
    }

    // Leitor rápido para evitar Time Limit Exceeded (TLE)
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