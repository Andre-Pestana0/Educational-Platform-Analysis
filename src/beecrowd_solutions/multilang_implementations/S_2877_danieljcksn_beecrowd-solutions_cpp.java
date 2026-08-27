import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int findMex(Set<Integer> arr) {
        int i = 0;
        while (true) {
            if (!arr.contains(i)) break;
            i++;
        }
        return i;
    }

    static int xorr(int[] arr) {
        int ans = 0;
        for (int val : arr) {
            ans ^= val;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int[][] m = new int[101][101];

        // Posições proibidas
        for (int i = 0; i <= 100; ++i)
            m[i][0] = 1000;
        for (int i = 0; i <= 100; ++i)
            m[0][i] = 1000;
        for (int i = 0; i <= 100; ++i)
            m[i][i] = 1000;

        // Estados perdedores
        m[1][2] = 0;
        m[2][1] = 0;

        Set<Integer> acessivel = new HashSet<>();
        for (int i = 1; i <= 100; ++i) {
            for (int j = 1; j <= 100; ++j) {
                if (m[i][j] != 1000) {
                    for (int k = j - 1; k >= 1; --k)
                        acessivel.add(m[i][k]);
                    for (int k = i - 1; k >= 1; --k)
                        acessivel.add(m[k][j]);
                    for (int k = 1; i - k > 0 && j - k > 0; k++)
                        acessivel.add(m[i - k][j - k]);

                    m[i][j] = findMex(acessivel);
                }
                acessivel.clear();
            }
        }

        String line = reader.readLine();
        if (line == null) return;
        st = new StringTokenizer(line);
        if (!st.hasMoreTokens()) return;

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        for (int i = 0; i < n; ++i) {
            while (st == null || !st.hasMoreTokens()) {
                line = reader.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = m[x][y];
        }

        if (xorr(arr) == 0) {
            System.out.println("N");
        } else {
            System.out.println("Y");
        }
    }
}