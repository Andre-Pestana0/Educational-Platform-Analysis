//BEECROWD - 1068

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) throws IOException {     
        Scanner entrada = new Scanner(System.in);
        Queue<Character> pilha = new LinkedList<Character>();
        int tam = 0;
        int i = 0;
        String texto = "";
        while(entrada.hasNextLine()){
            String expressao = entrada.nextLine();
            while(!pilha.isEmpty()) {
                pilha.poll();
            }
            tam = expressao.length();
            
            char expressaoVetor[] = expressao.toCharArray();
            
            for(i = 0; i < tam; i++) {
                if(expressaoVetor[i] == '('){
                    pilha.add('(');
                } else if (expressaoVetor[i] == ')') {
                    if(pilha.isEmpty()) {
                        break;
                    } else {
                        pilha.poll();
                    }
                }
            }
            if(i == tam && pilha.isEmpty()) {
                texto+="correct\n";
            } else {
                texto+="incorrect\n";
            }
        }
        entrada.close();
        System.out.println(texto);
    }
}