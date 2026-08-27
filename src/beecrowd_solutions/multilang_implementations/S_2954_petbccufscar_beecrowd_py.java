import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String line = reader.readLine();
        if (line == null) return;
        
        int n = Integer.parseInt(line.trim());

        // Regex para localizar pontuações/espaços e a palavra 'jogo' ou 'perdi'
        Pattern pattern = Pattern.compile("[ ,.:;]*(jogo|perdi)[ ,.:;$]*");

        while (n-- > 0) {
            String frase = reader.readLine();
            if (frase == null) break;

            frase = frase.toLowerCase().replace("$", "");

            Matcher matcher = pattern.matcher(frase);
            
            // Substitui pela palavra capturada ('jogo' ou 'perdi') seguida de '$'
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, matcher.group(1) + "\\$");
            }
            matcher.appendTail(sb);

            String[] jogos = sb.toString().split("\\$");

            int maxLetras = 0;

            for (String item : jogos) {
                int countLetras = 0;
                for (int i = 0; i < item.length(); i++) {
                    char c = item.charAt(i);
                    // Mantém apenas os caracteres alfabéticos (a-z)
                    if (Character.isLetter(c)) {
                        countLetras++;
                    }
                }
                if (countLetras > maxLetras) {
                    maxLetras = countLetras;
                }
            }

            System.out.println(maxLetras);
        }
    }
}