import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();

        String token = scanner.next();
        if (token == null) return;

        int andares = Integer.parseInt(token);
        int inicio = scanner.nextInt();
        int meta = scanner.nextInt();
        int cima = scanner.nextInt();
        int baixo = scanner.nextInt();

        if (inicio == meta) {
            System.out.println(0);
            return;
        }

        // Usa array de int para guardar distâncias. -1 indica "não visitado".
        int[] dist = new int[andares + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        
        dist[inicio] = 0;
        queue.add(inicio);

        boolean achou = false;

        while (!queue.isEmpty()) {
            int atual = queue.poll();

            if (atual == meta) {
                achou = true;
                break;
            }

            // Próximo andar para CIMA
            int proximoCima = atual + cima;
            if (proximoCima <= andares && dist[proximoCima] == -1) {
                dist[proximoCima] = dist[atual] + 1;
                queue.add(proximoCima);
            }

            // Próximo andar para BAIXO
            int proximoBaixo = atual - baixo;
            if (proximoBaixo >= 1 && dist[proximoBaixo] == -1) {
                dist[proximoBaixo] = dist[atual] + 1;
                queue.add(proximoBaixo);
            }
        }

        if (dist[meta] != -1) {
            System.out.println(dist[meta]);
        } else {
            System.out.println("use the stairs");
        }
    }

    static class FastScanner {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}