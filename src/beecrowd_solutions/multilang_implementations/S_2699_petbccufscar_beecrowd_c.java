import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static char[] s;
    static char[] d;
    static int[][] verificador = new int[1001][1001];

    static int divisao(int posicao, int resto, int divisor) {
        if (posicao == s.length) {
            if (resto == 0)
                return 1;
            else
                return 0;
        }

        if (verificador[posicao][resto] != -1)
            return verificador[posicao][resto];

        if (s[posicao] != '?') {
            return divisao(posicao + 1, (resto * 10 + (s[posicao] - '0')) % divisor, divisor);
        }

        int i;
        if (posicao == 0)
            i = 1;
        else
            i = 0;

        while (i < 10) {
            if (divisao(posicao + 1, (resto * 10 + i) % divisor, divisor) == 1) {
                d[posicao] = (char) ('0' + i);
                return 1;
            }
            i++;
        }

        verificador[posicao][resto] = 0;
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            String str = st.nextToken();
            int n = Integer.parseInt(st.nextToken());

            s = str.toCharArray();
            d = str.toCharArray();

            for (int i = 0; i < 1001; i++) {
                Arrays.fill(verificador[i], -1);
            }

            if (divisao(0, 0, n) == 1) {
                System.out.println(new String(d));
            } else {
                System.out.println("*");
            }
        }
    }
}