import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        String line = reader.readLine();
        if (line == null) return;

        int n = Integer.parseInt(line.trim());

        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            if (input == null) break;

            char[] frase = input.toCharArray();
            int len = frase.length;
            int j = 0;

            while (j < len - 8) {
                if (frase[j] == 'o' && frase[j + 1] == 'u' && frase[j + 2] == 'l' && frase[j + 3] == 'u'
                        && frase[j + 4] == 'p' && frase[j + 5] == 'u' && frase[j + 6] == 'k' && frase[j + 7] == 'k') {
                    
                    if (j - 1 >= 0) {
                        frase[j - 1] = 'J';
                    }
                    if (j + 8 < len) {
                        frase[j + 8] = 'i';
                    }
                }
                j++;
            }

            output.append(new String(frase)).append('\n');
        }

        System.out.print(output);
    }
}