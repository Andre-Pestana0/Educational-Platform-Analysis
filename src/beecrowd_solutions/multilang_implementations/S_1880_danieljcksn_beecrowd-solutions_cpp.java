import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Verifica se o número n é capicua (palíndromo) na base informada
    public static boolean baseN(long n, int base) {
        List<Integer> conv = new ArrayList<>();

        // Converte o número para a base informada através das divisões sucessivas
        while (n > 0) {
            int resto = (int) (n % base);
            conv.add(resto);
            n /= base;
        }

        // Verifica se é palíndromo comparando o início com o fim (sem precisar duplicar a lista)
        int i = 0;
        int j = conv.size() - 1;

        while (i < j) {
            if (!conv.get(i).equals(conv.get(j))) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        Integer tTokens = scanner.nextInt();
        if (tTokens == null) return;
        int t = tTokens;

        List<Integer> bases = new ArrayList<>();

        while (t-- > 0) {
            Long nToken = scanner.nextLong();
            if (nToken == null) break;
            long n = nToken;

            // Testa todas as bases de 2 até 16
            for (int i = 2; i <= 16; i++) {
                if (baseN(n, i)) {
                    bases.add(i);
                }
            }

            // Caso não seja capicua em nenhuma das bases
            if (bases.isEmpty()) {
                output.append("-1\n");
            } else {
                for (int i = 0; i < bases.size() - 1; i++) {
                    output.append(bases.get(i)).append(" ");
                }
                // Imprime a última resposta com quebra de linha
                output.append(bases.get(bases.size() - 1)).append("\n");
            }

            // Limpa as respostas para o próximo caso de teste
            bases.clear();
        }

        System.out.print(output);
    }

    // Leitor rápido para evitar Time Limit Exceeded (TLE)
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
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

        Integer nextInt() {
            String s = next();
            if (s == null) return null;
            return Integer.parseInt(s);
        }

        Long nextLong() {
            String s = next();
            if (s == null) return null;
            return Long.parseLong(s);
        }
    }
}