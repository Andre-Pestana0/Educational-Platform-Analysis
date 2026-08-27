import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static final int MAX_SIZE = 2000000;
    static boolean[] isprime = new boolean[MAX_SIZE + 1];
    static List<Integer> prime = new ArrayList<>();
    static int[] SPF = new int[MAX_SIZE + 1];

    // Encontra todos os primos menores que N em O(N)
    static void eratosthenes_sieve(int N) {
        Arrays.fill(isprime, true);
        isprime[0] = false;
        isprime[1] = false;

        for (int i = 2; i < N; i++) {
            if (isprime[i]) {
                prime.add(i);
                SPF[i] = i;
            }

            for (int j = 0; j < prime.size() && (long) i * prime.get(j) < N && prime.get(j) <= SPF[i]; j++) {
                isprime[i * prime.get(j)] = false;
                SPF[i * prime.get(j)] = prime.get(j);
            }
        }
    }

    // O(sqrt(n))
    static int countDivisors(int n) {
        int divisores = 0;
        for (int i = 1; i * i <= n; ++i) {
            if (n % i == 0) {
                if (n / i != i)
                    divisores += 2;
                else
                    divisores++;
            }
        }
        return divisores;
    }

    public static void main(String[] args) throws IOException {
        eratosthenes_sieve(MAX_SIZE + 1);

        // Clona o array isprime
        boolean[] c_isprime = Arrays.copyOf(isprime, isprime.length);
        int[] ans = new int[MAX_SIZE + 1];

        for (int i = 1; i * i <= MAX_SIZE; ++i) {
            if (isprime[countDivisors(i * i)]) {
                c_isprime[i * i] = true;
            }
        }

        int res = 0;
        for (int i = 2; i <= MAX_SIZE; ++i) {
            if (c_isprime[i]) {
                res++;
            }
            ans[i] = res;
        }

        // Leitura rápida de I/O para Java
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            int n = Integer.parseInt(line);
            output.append(ans[n]).append('\n');
        }

        System.out.print(output);
    }
}