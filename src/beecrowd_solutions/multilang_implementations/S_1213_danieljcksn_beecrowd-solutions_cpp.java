import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            int x = Integer.parseInt(line);
            int n = 1;
            int digitos = 1;

            while (n % x != 0) {
                n = (10 * n + 1) % x;
                digitos++;
            }

            System.out.println(digitos);
        }
    }
}