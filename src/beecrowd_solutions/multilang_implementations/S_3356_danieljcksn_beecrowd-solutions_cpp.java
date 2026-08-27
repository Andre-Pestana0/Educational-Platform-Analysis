import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static Set<Integer> getParents(List<List<Integer>> graph, int a) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];

        visited[a] = true;
        q.add(a);

        Set<Integer> parents = new HashSet<>();
        parents.add(a);

        while (!q.isEmpty()) {
            int t = q.poll();

            for (int adj : graph.get(t)) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    q.add(adj);
                    parents.add(adj);
                }
            }
        }

        return parents;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        Map<String, Integer> id = new HashMap<>();

        for (int i = 0; i < c; ++i) {
            st = ensureTokens(st, reader);
            String p1 = st.nextToken();
            String p2 = st.nextToken();
            String son = st.nextToken();

            id.putIfAbsent(p1, id.size() + 1);
            id.putIfAbsent(p2, id.size() + 1);
            id.putIfAbsent(son, id.size() + 1);

            graph.get(id.get(son)).add(id.get(p1));
            graph.get(id.get(son)).add(id.get(p2));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; ++i) {
            st = ensureTokens(st, reader);
            String a = st.nextToken();
            String b = st.nextToken();

            Set<Integer> parentsA = getParents(graph, id.get(a));
            Set<Integer> parentsB = getParents(graph, id.get(b));

            boolean flag = false;

            for (int parent : parentsA) {
                if (parentsB.contains(parent)) {
                    flag = true;
                    break;
                }
            }

            sb.append(flag ? "verdadeiro\n" : "falso\n");
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