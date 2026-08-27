import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        // Leitura da quantidade de soldados e pares de inimigos
        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        
        st = ensureTokens(st, reader);
        int m = Integer.parseInt(st.nextToken());

        // Criando listas vazias de inimigos para cada soldado
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = ensureTokens(st, reader);
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            // Adiciona os inimigos na lista respectiva
            g.get(a).add(b);
            g.get(b).add(a);
        }

        // Todos começam com a cor 2 (não visitados)
        int[] col = new int[n];
        Arrays.fill(col, 2);

        // Vetor de número de inimigos na mesma cor/grupo
        int[] enem = new int[n];

        for (int i = 0; i < n; i++) {
            // Contagem atual de inimigos com as cores 0, 1 e 2
            int[] cnt = new int[3];

            // Percorre todos os inimigos de i
            for (int j = 0; j < g.get(i).size(); j++) {
                int index = col[g.get(i).get(j)];
                cnt[index]++;
            }

            int color = 0;
            int cur = i;

            // Se a contagem de inimigos na cor 0 for maior que 1, escolhe a cor 1
            if (cnt[0] > 1) {
                color = 1;
            }

            while (true) {
                col[cur] = color;
                int ncur = -1;

                // Percorre os inimigos do soldado atual
                for (int j = 0; j < g.get(cur).size(); j++) {
                    int neighbor = g.get(cur).get(j);

                    if (col[neighbor] == color) {
                        enem[cur]++;
                        enem[neighbor]++;

                        // Se ultrapassou o limite de 1 inimigo da mesma cor
                        if (enem[neighbor] == 2) {
                            ncur = neighbor;

                            // Reverte a contagem de conflitos para reavaliar a cor
                            for (int l = 0; l < g.get(ncur).size(); l++) {
                                int ncurNeighbor = g.get(ncur).get(l);
                                if (col[ncurNeighbor] == color) {
                                    enem[ncurNeighbor]--;
                                    enem[ncur]--;
                                }
                            }
                        }
                    }
                }

                // Se não houve conflitos com limite excedido, finalizou a atribuição
                if (ncur == -1) {
                    break;
                }

                cur = ncur;
                color = (color == 1) ? 0 : 1;
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());

        // Organiza os soldados nos seus respectivos grupos
        for (int i = 0; i < n; i++) {
            res.get(col[i]).add(i);
        }

        StringBuilder sb = new StringBuilder();

        // Quantidade de cores utilizadas (1 ou 2)
        sb.append(res.get(1).size() > 0 ? 2 : 1).append('\n');

        // Formatação exata de saída por grupo
        for (int i = 0; i < 2; i++) {
            int size = res.get(i).size();
            for (int j = 0; j < size; j++) {
                sb.append(res.get(i).get(j) + 1);
                if (j + 1 < size) {
                    sb.append(" ");
                } else {
                    sb.append('\n');
                }
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