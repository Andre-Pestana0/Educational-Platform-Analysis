import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static int mod3(String num) {
        int ans = 0;

        for (int i = 0; i < num.length(); i++) {
            ans = (ans * 10 + (num.charAt(i) - '0')) % 3;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();

        if (num != null && !num.trim().isEmpty()) {
            System.out.println(mod3(num.trim()));
        }
    }
}