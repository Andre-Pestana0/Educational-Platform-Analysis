import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        if (line == null || line.trim().isEmpty()) {
            return;
        }

        StringTokenizer st = new StringTokenizer(line);

        int l = Integer.parseInt(st.nextToken());
        
        while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());

        while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());

        while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());

        String ans;

        if (((c - 1) & 1) != 0) {
            if ((y & 1) != 0) {
                ans = "Esquerda";
            } else {
                ans = "Direita";
            }
        } else {
            if ((x & 1) != 0) {
                if ((y & 1) != 0) {
                    ans = "Direita";
                } else {
                    ans = "Esquerda";
                }
            } else {
                if ((y & 1) != 0) {
                    ans = "Esquerda";
                } else {
                    ans = "Direita";
                }
            }
        }

        System.out.println(ans);
    }
}