import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());

        st = ensureTokens(st, reader);
        int q = Integer.parseInt(st.nextToken());

        int[] employees = new int[n];

        for (int i = 0; i < n; ++i) {
            employees[i] = i;
        }

        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            st = ensureTokens(st, reader);
            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // Swap dos elementos na posição 0-indexed
                int temp = employees[a - 1];
                employees[a - 1] = employees[b - 1];
                employees[b - 1] = temp;
            } else {
                int e = Integer.parseInt(st.nextToken());
                int ans = 0;

                int i = e - 1;

                while (employees[i] != e - 1) {
                    i = employees[i];
                    ans++;
                }

                sb.append(ans).append('\n');
            }
        }

        System.out.print(sb.toString());
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