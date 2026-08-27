import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    // Retorna o tamanho da maior subsequência comum entre as strings X e Y
    private static int lcs(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        int[][] L = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    L[i][j] = 0;
                } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    L[i][j] = L[i - 1][j - 1] + 1;
                } else {
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                }
            }
        }

        return L[m][n];
    }

    // Reconstrói a sequência de caracteres visitados na grade
    private static String criaSequencia(String[] grade, BufferedReader br) throws IOException {
        String line = br.readLine();
        while (line == null || line.trim().isEmpty()) {
            line = br.readLine();
        }

        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        String passos = "";
        if (n != 0) {
            passos = br.readLine().trim();
        }

        StringBuilder result = new StringBuilder();
        int linhaAtual = x - 1;
        int colunaAtual = y - 1;

        result.append(grade[linhaAtual].charAt(colunaAtual));

        for (int i = 0; i < n; i++) {
            char direcao = passos.charAt(i);
            switch (direcao) {
                case 'N':
                    linhaAtual--;
                    break;
                case 'S':
                    linhaAtual++;
                    break;
                case 'E':
                    colunaAtual++;
                    break;
                case 'W':
                    colunaAtual--;
                    break;
            }
            result.append(grade[linhaAtual].charAt(colunaAtual));
        }

        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (line == null) return;
        int t = Integer.parseInt(line.trim());

        for (int cases = 1; cases <= t; cases++) {
            line = br.readLine();
            while (line == null || line.trim().isEmpty()) {
                line = br.readLine();
            }

            StringTokenizer st = new StringTokenizer(line);
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            String[] grade = new String[h];
            for (int i = 0; i < h; i++) {
                grade[i] = br.readLine().trim();
            }

            String sA = criaSequencia(grade, br);
            String sB = criaSequencia(grade, br);

            int msc = lcs(sA, sB);
            int remA = sA.length() - msc;
            int remB = sB.length() - msc;

            System.out.println("Case " + cases + ": " + remA + " " + remB);
        }
    }
}