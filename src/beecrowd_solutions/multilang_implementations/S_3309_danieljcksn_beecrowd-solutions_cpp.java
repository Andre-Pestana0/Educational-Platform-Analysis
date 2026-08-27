import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int happyNumber(int x) {
        int res = 0;

        while (x > 0) {
            int digit = x % 10;
            res += digit * digit;
            x /= 10;
        }

        return res;
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

        int ans = 0;

        while (n-- > 0) {
            st = ensureTokens(st, reader);
            int x = Integer.parseInt(st.nextToken());

            Set<Integer> previousValues = new HashSet<>();
            previousValues.add(x);

            while (true) {
                int res = happyNumber(x);

                if (res == 1) {
                    ans++;
                    break;
                }

                if (previousValues.contains(res)) {
                    break;
                }

                x = res;
                previousValues.add(x);
            }
        }

        System.out.println(ans);
    }

    private static StringTokenizer ensureTokens(StringTokenizer st, BufferedReader reader) throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) break;
            st = new StringTokenizer(line);
        }
        return st;
    }
}