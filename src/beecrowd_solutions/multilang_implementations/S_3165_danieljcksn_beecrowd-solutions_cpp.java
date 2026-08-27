import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static boolean isPrime(int n) {
        if (n < 2)
            return false;

        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; ++i) {
            if (n % i == 0 && n != i)
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());

        int p1 = 0;
        int p2 = 0;

        if (n % 2 == 0)
            n--;

        for (int i = n; i >= 5; i -= 2) {
            if (isPrime(i)) {
                if (isPrime(i - 2)) {
                    p1 = i - 2;
                    p2 = i;
                    break;
                }
            }
        }

        System.out.println(p1 + " " + p2);
    }
}