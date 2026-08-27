import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// g-otn
// URI_2866
// Criptotexto

public class URI_2866 {

    public static void main(String[] args) throws IOException {

        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

        int casosDeTeste = Integer.parseInt(leitor.readLine());
        for (int i = 0; i < casosDeTeste; i++) {
            StringBuilder mensagem = new StringBuilder();

            char caractere;

            do {
                caractere = (char)leitor.read();
                if (caractere > 96 && caractere < 123) {
                    mensagem.append(caractere);
                }
            } while (caractere != '\n');

            System.out.println(mensagem.reverse());
        }

    }

}
