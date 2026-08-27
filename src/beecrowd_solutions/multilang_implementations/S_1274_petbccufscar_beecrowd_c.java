import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            int[] target = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            boolean valid = true;

            // Lê a transformação {1 -> i1, 2 -> i2, ..., N -> iN}
            for (int i = 1; i <= n; i++) {
                while (!st.hasMoreTokens()) {
                    String nextLine = br.readLine();
                    if (nextLine == null) break;
                    st = new StringTokenizer(nextLine);
                }
                int val = Integer.parseInt(st.nextToken());
                target[i] = val;

                // Valida se a transformação é uma permutação bijetora válida
                if (val < 1 || val > n || visited[val]) {
                    valid = false;
                }
                visited[val] = true;
            }

            if (!valid) {
                System.out.println("No solution");
                continue;
            }

            // Construção da permutação: quer trazer cada elemento para a sua posição correta
            // p[i] representa qual fio de entrada chega na posição 'i' do lado direito
            int[] p = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                p[i] = target[i];
            }

            ArrayList<Integer> strokes = new ArrayList<>();

            // Simula os traços (strokes) para ordenar a permutação p
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n - 1; j++) {
                    if (p[j] > p[j + 1]) {
                        int temp = p[j];
                        p[j] = p[j + 1];
                        p[j + 1] = temp;

                        // Adiciona o traço conectando o fio j e j + 1
                        strokes.add(j);
                    }
                }
            }

            // Verifica se foi resolvido com sucesso
            boolean solved = true;
            for (int i = 1; i <= n; i++) {
                if (p[i] != i) {
                    solved = false;
                    break;
                }
            }

            if (!solved || strokes.size() >= 4 * n * n) {
                System.out.println("No solution");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(strokes.size());
                for (int s : strokes) {
                    sb.append(" ").append(s);
                }
                System.out.println(sb.toString());
            }
        }
    }
}