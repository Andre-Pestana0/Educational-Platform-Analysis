import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static long[][] PD = new long[201][51];
    static long[][] proj = new long[50][2];

    static long max(long a, long b) {
        return (a >= b) ? a : b;
    }

    // Função de Programação Dinâmica (Memoization)
    static long pd(int W, int idx) {
        if (idx == 0 && W >= proj[idx][1])
            return proj[0][0];
        else if (idx == 0)
            return 0;

        if (PD[W][idx] != -1)
            return PD[W][idx];

        long ans;
        if (W - proj[idx][1] >= 0)
            ans = max(proj[idx][0] + pd((int)(W - proj[idx][1]), idx - 1), pd(W, idx - 1));
        else
            ans = pd(W, idx - 1);

        return PD[W][idx] = ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        
        if (line == null) return;
        int t = Integer.parseInt(line.trim());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            // Limpa a tabela PD para cada caso de teste
            for (long[] row : PD) {
                Arrays.fill(row, -1);
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                proj[i][0] = Long.parseLong(st.nextToken()); // Dano
                proj[i][1] = Long.parseLong(st.nextToken()); // Peso
            }

            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            long castle_hp = Long.parseLong(st.nextToken());

            long ans = pd(W, n - 1);

            if (ans >= castle_hp) {
                System.out.println("Missao completada com sucesso");
            } else {
                System.out.println("Falha na missao");
            }
        }
    }
}