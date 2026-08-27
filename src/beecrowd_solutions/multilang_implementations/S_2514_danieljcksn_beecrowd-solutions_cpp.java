import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    // Função para calcular o Máximo Divisor Comum (MDC) usando o Algoritmo de Euclides
    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            long m = Long.parseLong(st.nextToken());
            
            // Garante a leitura dos próximos tokens mesmo se estiverem em linhas separadas
            while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            long l1 = Long.parseLong(st.nextToken());

            while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            long l2 = Long.parseLong(st.nextToken());

            while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            long l3 = Long.parseLong(st.nextToken());

            // Cálculo do MMC usando o tipo long para prevenir overflow intermediário
            long mmc = (l1 * l2) / gcd(l1, l2);
            mmc = (mmc * l3) / gcd(mmc, l3);

            System.out.println(mmc - m);
        }
    }
}