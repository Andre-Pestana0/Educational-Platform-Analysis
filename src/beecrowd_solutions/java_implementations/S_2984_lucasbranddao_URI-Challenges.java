import java.util.Scanner;

public class URI2984 {
 
    public static void main(String[] args){

        Scanner entrada = new Scanner(System.in);
        String form;
        PilhaLucas p = new PilhaLucas();

        form = entrada.nextLine();

        for (int i=0; i < form.length(); i++){
            char c = form.charAt(i);
            if (c == '('){
                p.push(c);
            }
            else if (c == ')'){
                if (!p.isEmpty()){
                    char x = p.pop();
                }
            }
        }
        if (p.isEmpty()){
            System.out.println("Partiu RU!");
        }
        else{
            String remaining = String.format("%d", (p.topo + 1));
            System.out.println("Ainda temos " + remaining + " assunto(s) pendente(s)!");
        }

    }

     
    private static class PilhaLucas{
        private int topo;
        private char[] itens;

        public PilhaLucas
        (){
            topo = -1;
            itens = new char[1000];
        }

        public boolean isEmpty(){
            return (topo == -1);
        }

        public void push(char e){
            topo++;
            itens[topo] = e;
        }

        public char pop(){
            char i = itens[topo];
            topo--;
            return i;
        }
    }
 
}