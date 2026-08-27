import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    // Memorização para os resultados
    static long[][][] dp = new long[20][180][2];

    // Armazena os dígitos de x em uma lista
    static void getDigits(long x, List<Integer> digit) {
        while (x > 0) {
            digit.add((int) (x % 10));
            x /= 10;
        }
    }

    // Retorna a contagem dos dígitos (1 e 7) de 1 até o inteiro
    // armazenado na lista digit.
    static long digitSum(int idx, int sum, int tight, List<Integer> digit) {
        // Caso base
        if (idx == -1)
            return sum;

        // Checando se esse estado foi calculado
        if (dp[idx][sum][tight] != -1 && tight != 1)
            return dp[idx][sum][tight];

        long ret = 0;

        // Calculando o limite do intervalo para a posição atual
        int k = (tight == 1) ? digit.get(idx) : 9;

        for (int i = 0; i <= k; i++) {
            // Calculando o valor de newTight para o próximo estado
            int newTight = (digit.get(idx) == i) ? tight : 0;

            // Se o dígito i for igual a 1 ou 7
            if (i == 7 || i == 1)
                ret += digitSum(idx - 1, sum + 1, newTight, digit);
            else
                ret += digitSum(idx - 1, sum, newTight, digit);
        }

        if (tight == 0)
            dp[idx][sum][tight] = ret;

        return ret;
    }

    // Retorna a soma dos dígitos dos inteiros no intervalo [1, a]
    static long rangeDigitSum(long a) {
        // Inicializando a tabela de memorização com -1
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 180; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        // Armazenando os dígitos de 'a' na lista
        List<Integer> digit = new ArrayList<>();
        getDigits(a, digit);

        // O índice inicial é digit.size() - 1 para apontar corretamente para o último elemento
        long ans1 = digitSum(digit.size() - 1, 0, 1, digit);

        return ans1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        long a = Long.parseLong(st.nextToken());

        System.out.println(rangeDigitSum(a));
    }
}