import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    // Função para imprimir a matriz formatada com 'X' e 'O'
    public static void printMatrix(char[][] matrix, StringBuilder output) {
        int n = matrix.length;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                output.append(matrix[r][c] == 'X' ? 'X' : 'O');
            }
            output.append('\n');
        }
        output.append("@\n");
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        while (true) {
            Integer nToken = scanner.nextInt();
            if (nToken == null || nToken == 0) {
                break; // Termina o programa se n for 0 ou se atingir EOF
            }

            int n = nToken;

            // Inicializa a matriz com 'O's
            char[][] matrix = new char[n][n];
            for (int r = 0; r < n; r++) {
                Arrays.fill(matrix[r], 'O');
            }

            // Inicializa as variáveis para rastrear a posição do 'X' na matriz
            int lin = n / 2;
            int col = n / 2 + 1;
            int i = n / 2;
            int j = n / 2;
            int go = 0; // Direção inicial (0 - direita, 1 - cima, 2 - esquerda, 3 - baixo)
            int k = 1;  // Variável para controlar o deslocamento da espiral

            while (true) {
                // Coloca 'X' na posição atual e imprime a matriz
                matrix[i][j] = 'X';
                printMatrix(matrix, output);

                // Evita estouro do buffer de saída enviando em blocos
                if (output.length() > 32768) {
                    System.out.print(output);
                    output.setLength(0);
                }

                // Verifica se atingiu o final da espiral
                if (i == n - 1 && j == n - 1) {
                    break;
                }

                // Coloca 'O' na posição atual
                matrix[i][j] = 'O';

                // Atualiza a posição com base na direção atual
                if (go == 2) {
                    j -= 1;
                } else if (go == 0) {
                    j += 1;
                } else if (go == 1) {
                    i -= 1;
                } else if (go == 3) {
                    i += 1;
                }

                // Verifica se é necessário mudar a direção
                if (j == col && i == lin) {
                    if (go == 0) {
                        lin -= k;
                        go = 1;
                    } else if (go == 1) {
                        k += 1;
                        col -= k;
                        go = 2;
                    } else if (go == 2) {
                        lin += k;
                        go = 3;
                    } else if (go == 3) {
                        k += 1;
                        col += k;
                        go = 0;
                    }
                }
            }

            // Coloca 'O' na última posição da espiral
            matrix[i][j] = 'O';
        }

        // Imprime qualquer texto restante no buffer
        if (output.length() > 0) {
            System.out.print(output);
        }
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

        Integer nextInt() {
            String s = next();
            if (s == null) return null;
            return Integer.parseInt(s);
        }
    }
}