import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static void computeLPSArray(char[] pat, int M, int[] lps) {
        int len = 0;
        lps[0] = 0;

        int i = 1;
        while (i < M) {
            if (pat[i] == pat[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    static int KMPSearch(char[] pat, char[] txt) {
        int ret = 0;
        int M = pat.length;
        int N = txt.length;

        int[] lps = new int[M];
        computeLPSArray(pat, M, lps);

        int i = 0;
        int j = 0;

        while (i < N - 1) {
            if (pat[j] == txt[i]) {
                j++;
                i++;
            }

            if (j == M) {
                ret++;
                j = lps[j - 1];
            } else if (i < N && pat[j] != txt[i]) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        if (input == null || input.trim().isEmpty()) return;

        char[] strBase = input.trim().toCharArray();
        int len = strBase.length;

        int i = 0, j = 1, k = 0;
        while (k < len && i < len && j < len) {
            int idx1 = (i + k) % len;
            int idx2 = (j + k) % len;

            if (strBase[idx1] == strBase[idx2]) {
                k++;
            } else {
                if (strBase[idx1] < strBase[idx2]) {
                    i = i + k + 1;
                } else {
                    j = j + k + 1;
                }
                if (i == j) {
                    i++;
                }
                k = 0;
            }
        }

        int maxIdx = Math.min(i, j);

        // Constrói o padrão `pat` com a menor rotação lexicográfica
        char[] pat = new char[len];
        int sz = 0;
        for (k = maxIdx; k < len + maxIdx; ++k) {
            pat[sz++] = strBase[k % len];
        }

        // Constrói o texto dobrado `txt` (equivalente ao str[2*len] em C)
        char[] txt = new char[2 * len];
        for (i = 0; i < len; ++i) {
            txt[i] = strBase[i];
            txt[i + len] = strBase[i];
        }

        System.out.printf("%d %d%n", KMPSearch(pat, txt), maxIdx + 1);
    }
}