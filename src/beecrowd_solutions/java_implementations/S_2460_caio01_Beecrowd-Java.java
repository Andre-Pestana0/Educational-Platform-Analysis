//BEECROWD - 2460

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) throws IOException {
 
        Scanner entrada = new Scanner(System.in);
        int qtdPessoasFila = entrada.nextInt();
        entrada.nextLine();
        String filaInicioString = entrada.nextLine();
        int qtdPessoasSairamFila = entrada.nextInt();
        entrada.nextLine();
        String filaFimString = entrada.nextLine();

        entrada.close();

        Queue<String> filaInicio = new LinkedList<String>();
        String[] filaInicioVetor = filaInicioString.split(" ");
        for(int k = 0; k < filaInicioVetor.length; k++) {
            filaInicio.add(filaInicioVetor[k]);
        }

        Queue<String> filaFim = new LinkedList<String>();
        String[] filaFimVetor = filaFimString.split(" ");
        for(int k = 0; k < filaFimVetor.length; k++) {
            filaFim.add(filaFimVetor[k]);
        }


        int qtdSairam = qtdPessoasSairamFila;
        while(qtdSairam > 0) {
            filaInicio.remove(filaFim.poll());
            qtdSairam--;
        }

        String texto = "";
        for(int w = 0; w < (qtdPessoasFila - qtdPessoasSairamFila); w++) {
            if(w == ((qtdPessoasFila - qtdPessoasSairamFila) - 1)) {
                texto += filaInicio.poll();
            } else {
                texto += (filaInicio.poll() + " ");
            }
            
        }

        System.out.println(texto);
    }
}