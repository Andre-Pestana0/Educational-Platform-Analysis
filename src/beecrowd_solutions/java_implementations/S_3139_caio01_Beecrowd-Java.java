//BEECROWD - 3139

import java.io.IOException;
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) throws IOException {
 
        Scanner entrada = new Scanner(System.in);
        String nSeguidores = entrada.nextLine();
        String diasMes = entrada.nextLine();

        entrada.close();

        int nSeguidoresInt[] = new int[2];
        String[] nSeguidoresString = nSeguidores.split(" ");
        for(int j = 0; j < 2; j++) {
            nSeguidoresInt[j] = Integer.parseInt(nSeguidoresString[j]);
        }

        int diasMesInt[] = new int[30];
        double soma = 0;
        String[] diasMesString = diasMes.split(" ");
        for(int k = 0; k < 30; k++) {
            diasMesInt[k] = Integer.parseInt(diasMesString[k]);
            soma += diasMesInt[k];
        }

        double atual = nSeguidoresInt[0];
        double objetivo = nSeguidoresInt[1];

        double media = 0;
        int count = 0;

        while (atual < objetivo) {
            for(int a = 0; a < 30 && atual < objetivo; ++a) {
                media = soma/30;
                if(media != Math.round(media)){
                    media = Math.round(media + 0.5);
                }
                atual += media;
                soma = soma + (media - diasMesInt[a]);
                diasMesInt[a] = (int)media;
                count++;
            }
        }

        System.out.println(count);
    }
}