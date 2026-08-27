// URI Online Judge | 2242
// Huaauhahhuahau

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class URI_2242 {

    public static void main(String[] args) throws IOException {

        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

        String risada = "";

        int caractere;
        do {
            caractere = leitor.read();
            if (caractere == 97 || caractere == 101 || caractere == 105 || caractere == 111 || caractere == 117)
                risada += (char) caractere;
        } while (caractere != 10);

//        System.out.println("risada: " + risada);

        int lengthMenos1 = risada.length() - 1;
        int metade = (lengthMenos1 + 1) / 2;

        for (int i = 0; i < metade; i++) {
            if (risada.charAt(i) != risada.charAt(lengthMenos1 - i)) {
                System.out.println("N");
                return;
            }
        }

        System.out.println("S");
    }

}
