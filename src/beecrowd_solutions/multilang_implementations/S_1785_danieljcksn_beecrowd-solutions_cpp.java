import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int contaDigitos(int x) {
        int cont = 0;

        if (x == 0)
            return 1;

        while (x > 0) {
            cont++;
            x /= 10;
        }

        return cont;
    }

    // Verifica se há, pelo menos, dois dígitos distintos entre si.
    public static boolean verificaDistintos(int X) {
        int digito, primeiro = X % 10;

        // Para valores menores que 1000, considera que há zeros à esquerda tornando-os distintos
        if (X < 1000)
            return true;

        X /= 10;
        while (X > 0) {
            digito = X % 10;
            if (digito != primeiro)
                return true;
            X /= 10;
        }
        return false;
    }

    public static int maiorNumeroComDigitosDe(List<Integer> num) {
        int maiorNumero = 0;

        // Ordena decrescentemente
        Collections.sort(num, Collections.reverseOrder());

        maiorNumero += num.get(0) * 1000;
        maiorNumero += num.get(1) * 100;
        maiorNumero += num.get(2) * 10;
        maiorNumero += num.get(3);

        return maiorNumero;
    }

    public static int menorNumeroComDigitosDe(List<Integer> num) {
        int menorNumero = 0;

        // Ordena crescentemente
        Collections.sort(num);

        menorNumero += num.get(0) * 1000;
        menorNumero += num.get(1) * 100;
        menorNumero += num.get(2) * 10;
        menorNumero += num.get(3);

        return menorNumero;
    }

    public static int krapekar(int X) {
        int cnt = 0, aux, maior, menor;
        List<Integer> digitos = new ArrayList<>();

        boolean distintos = verificaDistintos(X);
        if (distintos) {
            while (X != 6174) {
                aux = X;
                while (aux > 0) {
                    digitos.add(aux % 10);
                    aux /= 10;
                }
                if (contaDigitos(X) < 4) {
                    int zerosFaltantes = 4 - contaDigitos(X);
                    for (int i = 0; i < zerosFaltantes; i++) {
                        digitos.add(0);
                    }
                }

                maior = maiorNumeroComDigitosDe(digitos);
                menor = menorNumeroComDigitosDe(digitos);
                X = maior - menor;
                if (X == 0)
                    return -1;

                digitos.clear();
                cnt++;
            }
        } else {
            cnt = -1;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        Integer tTokens = scanner.nextInt();
        if (tTokens == null) return;
        int t = tTokens;

        int casos = 1;

        while (t-- > 0) {
            Integer xTokens = scanner.nextInt();
            if (xTokens == null) break;
            int x = xTokens;

            if (x == 0) {
                output.append("Caso #").append(casos).append(": -1\n");
            } else {
                output.append("Caso #").append(casos).append(": ").append(krapekar(x)).append('\n');
            }

            casos++;
        }

        System.out.print(output);
    }

    // Leitor rápido para IO otimizado
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