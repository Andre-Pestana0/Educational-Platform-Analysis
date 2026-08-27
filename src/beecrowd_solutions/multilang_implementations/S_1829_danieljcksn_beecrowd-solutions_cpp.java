import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        Integer nTokens = scanner.nextInt();
        if (nTokens == null) return;
        int n = nTokens;

        List<Boolean> rodadas = new ArrayList<>();
        int lucas = 0;
        int pedro = 0;

        for (int k = 0; k < n; k++) {
            String expressao = scanner.next();
            if (expressao == null) break;

            // Separa a base e o expoente pelo caractere '^'
            String[] partes = expressao.split("\\^");
            int bas = Integer.parseInt(partes[0]);
            int exp = Integer.parseInt(partes[1]);

            String f = scanner.next();
            // Remove o caractere '!' do final da string
            f = f.substring(0, f.length() - 1);
            int fat = Integer.parseInt(f);

            // Cálculo do logaritmo do Fatorial: log(fat!) = log(fat) + log(fat-1) + ... + log(1)
            double fatorial = 0;
            for (int i = fat; i >= 1; i--) {
                fatorial += Math.log(i);
            }

            // Cálculo do logaritmo da Exponenciação: log(bas^exp) = exp * log(bas)
            double exponenciacao = exp * Math.log(bas);

            // Se a exponenciação for maior, Lucas vence (true), senão Pedro vence (false)
            if (exponenciacao > fatorial) {
                rodadas.add(true);
                lucas++;
            } else {
                rodadas.add(false);
                pedro++;
            }
        }

        // Placar final
        if (lucas == pedro) {
            output.append("A competicao terminou empatada!\n");
        } else if (lucas > pedro) {
            output.append("Campeao: Lucas!\n");
        } else {
            output.append("Campeao: Pedro!\n");
        }

        // Imprime os vencedores de cada rodada
        int numRodada = 1;
        for (boolean venceuLucas : rodadas) {
            if (venceuLucas) {
                output.append("Rodada #").append(numRodada).append(": Lucas foi o vencedor\n");
            } else {
                output.append("Rodada #").append(numRodada).append(": Pedro foi o vencedor\n");
            }
            numRodada++;
        }

        System.out.print(output);
    }

    // Leitor rápido de I/O para evitar TLE no Beecrowd
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
    }
}