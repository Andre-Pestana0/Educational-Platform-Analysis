import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();
        
        char[] tabela = "0123456789ABCDEFGHIJKLMNOPQRSTUV".toCharArray();

        while (true) {
            String token = scanner.next();
            if (token == null) break;

            // Long.parseUnsignedLong lida com o equivalente ao 'unsigned long long' do C
            long n = Long.parseUnsignedLong(token);

            if (n == 0) {
                output.append("0\n");
                break;
            }

            int[] num = new int[13];
            int inseridos = 0;

            while (n != 0) {
                // Long.remainderUnsigned e divideUnsigned garantem operação sem sinal
                int resto = (int) Long.remainderUnsigned(n, 32);
                n = Long.divideUnsigned(n, 32);
                
                num[inseridos] = resto;
                inseridos++;
            }

            for (int i = inseridos - 1; i >= 0; i--) {
                int indice = num[i];
                output.append(tabela[indice]);
            }
            output.append('\n');
        }

        System.out.print(output);
    }

    // Leitor rápido de I/O otimizado
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
    }
}