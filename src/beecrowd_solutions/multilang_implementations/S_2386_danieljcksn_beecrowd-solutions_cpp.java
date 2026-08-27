import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (line == null || line.trim().isEmpty()) {
            return;
        }

        int a = Integer.parseInt(line.trim());
        int n = Integer.parseInt(br.readLine().trim());
        int cont = 0;

        StringTokenizer st = null;

        for (int i = 0; i < n; i++) {
            while (st == null || !st.hasMoreTokens()) {
                line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }

            if (st != null && st.hasMoreTokens()) {
                // Usamos long na multiplicação para evitar eventual overflow de int
                long f = Long.parseLong(st.nextToken());
                
                if (f * a >= 40000000L) {
                    cont++;
                }
            }
        }

        System.out.println(cont);
    }
}