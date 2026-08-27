import java.util.Scanner;

public class URI1069 {
 
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        int counter = Integer.parseInt(entrada.nextLine());

        for (int i=0; i<counter; i++){
            String input = entrada.nextLine();
            PilhaLucas p = new PilhaLucas();
            int diamonds = 0;

            for (int k=0; k < input.length(); k++){
                char c = input.charAt(k);
                if (c == '<'){
                    p.push(c);
                }
                else if (c == '>'){
                    if (!p.isEmpty()){
                        diamonds++;
                        p.pop();

                    }
                    
                }
            }
            System.out.println(diamonds);
        }
    }


    private static class PilhaLucas{
        private int topo;
        private char[] itens;

        public PilhaLucas(){
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
