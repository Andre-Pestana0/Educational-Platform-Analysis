//BEECROWD - 2160

import java.io.IOException;
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) throws IOException {
 
        Scanner entrada = new Scanner(System.in);
        String texto = entrada.nextLine();

        entrada.close();
        if (texto.length() <= 80) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}