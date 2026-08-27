import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());

        if (n == 1) {
            System.out.println(0);
            return;
        }

        int[] arr = new int[n + 3]; // Garante tamanho suficiente para os casos iniciais
        arr[1] = 1;
        arr[2] = 0;
        arr[3] = 1;

        for (int i = 4; i <= n; ++i) {
            arr[i] = (int) (((long) arr[i - 2] % MOD + arr[i - 3] % MOD) % MOD);
        }

        System.out.println(arr[n]);
    }
}