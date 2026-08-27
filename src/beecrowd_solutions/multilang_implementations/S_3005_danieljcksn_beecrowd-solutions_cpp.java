import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    // Verifica se o paralelepípedo 'a' pode ser empilhado sobre 'b'
    static boolean empilha(int[] a, int[] b) {
        if ((((a[0] < b[0]) && (a[1] < b[1])) || ((a[0] < b[1]) && (a[1] < b[0]))) || 
            (((a[0] < b[1]) && (a[1] < b[2])) || ((a[0] < b[2]) && (a[1] < b[1]))) || 
            (((a[0] < b[0]) && (a[1] < b[2])) || ((a[0] < b[2]) && (a[1] < b[0])))) {
            return true;
        } else if ((((a[1] < b[0]) && (a[2] < b[1])) || ((a[1] < b[1]) && (a[2] < b[0]))) || 
                   (((a[1] < b[1]) && (a[2] < b[2])) || ((a[1] < b[2]) && (a[2] < b[1]))) || 
                   (((a[1] < b[0]) && (a[2] < b[2])) || ((a[1] < b[2]) && (a[2] < b[0])))) {
            return true;
        } else if ((((a[0] < b[0]) && (a[2] < b[1])) || ((a[0] < b[1]) && (a[2] < b[0]))) || 
                   (((a[0] < b[1]) && (a[2] < b[2])) || ((a[0] < b[2]) && (a[2] < b[1]))) || 
                   (((a[0] < b[0]) && (a[2] < b[2])) || ((a[0] < b[2]) && (a[2] < b[0])))) {
            return true;
        }
        
        return false;
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

        int[] p = new int[3];
        int[] s = new int[3];

        StringBuilder sb = new StringBuilder();

        while (n-- > 0) {
            st = ensureTokens(st, reader);
            for (int i = 0; i < 3; i++) {
                p[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < 3; i++) {
                s[i] = Integer.parseInt(st.nextToken());
            }

            if (empilha(p, s) && empilha(s, p)) {
                sb.append("3\n");
            } else if (empilha(p, s)) {
                sb.append("1\n");
            } else if (empilha(s, p)) {
                sb.append("2\n");
            } else {
                sb.append("0\n");
            }
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