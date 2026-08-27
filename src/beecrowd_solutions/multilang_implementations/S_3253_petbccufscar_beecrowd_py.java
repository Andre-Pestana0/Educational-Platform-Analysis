import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    // Algoritmo de busca em profundidade (DFS)
    static boolean[] dfs(Map<Integer, List<Integer>> vetor) {
        // Pilha de ruas a serem visitadas
        Deque<Integer> pilha = new ArrayDeque<>();
        pilha.push(0);

        // Lista de ruas visitadas
        boolean[] visited = new boolean[1000];

        // Enquanto houver ruas na pilha
        while (!pilha.isEmpty()) {
            int aux = pilha.pop();

            if (!visited[aux]) {
                visited[aux] = true;

                // Adiciona as ruas ligadas através do vetor de adjacência na pilha
                if (vetor.containsKey(aux)) {
                    for (int rua : vetor.get(aux)) {
                        if (!visited[rua]) {
                            pilha.push(rua);
                        }
                    }
                }
            }
        }

        return visited;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int nroRuas = Integer.parseInt(st.nextToken());

        boolean noProblem = true;

        Map<Integer, List<Integer>> ruasAdjacentes = new HashMap<>();
        Map<Integer, List<Integer>> chegam = new HashMap<>();
        List<Integer> idInputOrder = new ArrayList<>();

        // Leitura de cada rua e seus caminhos
        for (int rua = 0; rua < nroRuas; rua++) {
            st = ensureTokens(st, reader);

            int id = Integer.parseInt(st.nextToken());
            int nroAlcancaveis = Integer.parseInt(st.nextToken());

            idInputOrder.add(id);

            List<Integer> alcancaveis = new ArrayList<>();
            for (int i = 0; i < nroAlcancaveis; i++) {
                int adj = Integer.parseInt(st.nextToken());
                alcancaveis.add(adj);

                // Preenche a estrutura de ruas que chegam em 'adj'
                chegam.putIfAbsent(adj, new ArrayList<>());
                chegam.get(adj).add(id);
            }

            ruasAdjacentes.put(id, alcancaveis);
        }

        // Marca as ruas que são possíveis acessar a partir da rua 0
        boolean[] visited = dfs(ruasAdjacentes);

        // Marca as ruas a partir das quais é possível chegar na rua 0
        boolean[] visited2 = dfs(chegam);

        StringBuilder trappeds = new StringBuilder();
        StringBuilder unreachables = new StringBuilder();

        // Percorre as ruas na ordem em que foram inseridas
        for (int id : idInputOrder) {
            // Verifica se foi possível acessar a rua 0 a partir da rua atual
            if (!visited2[id]) {
                noProblem = false;
                trappeds.append("TRAPPED ").append(id).append('\n');
            }

            // Verifica se foi possível acessar a rua atual a partir da rua 0
            if (!visited[id]) {
                noProblem = false;
                unreachables.append("UNREACHABLE ").append(id).append('\n');
            }
        }

        // Imprime o resultado final
        if (noProblem) {
            System.out.println("NO PROBLEMS");
        } else {
            System.out.print(trappeds.toString());
            System.out.print(unreachables.toString());
        }
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