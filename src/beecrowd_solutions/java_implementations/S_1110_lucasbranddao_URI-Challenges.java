import java.util.Scanner;

public class URI1110 {
 
    public static void main(String[] args){

        Scanner entrada = new Scanner(System.in);

        while (entrada.hasNext()){
            String input = entrada.nextLine();
            int n = Integer.parseInt(input); 
            boolean shouldDiscard = true;
            PilhaLucas p = new PilhaLucas();

            if (n == 0){
                break;
            }

            for(int i = n; i>=1; i--){
                p.push(i);
            }
            
            System.out.print("Discarded cards:");

            while(p.topo > 0){
                if (shouldDiscard){
                    int poped = p.pop();
                    if (p.topo == 0){
                        System.out.println(" " + poped);
                    }
                    else{
                        System.out.print(" " + poped + ",");
                    }
                }
                else{
                    int itemNoTopo = p.itens[p.topo];
                    for(int k = p.topo-1; k >= 0; k--){
                        p.itens[k+1] = p.itens[k]; 
                    }
                    p.itens[0] = itemNoTopo;
                }
                shouldDiscard = !shouldDiscard;
            }
            System.out.println("Remaining card: " + p.pop());
        }
    }

     
    private static class PilhaLucas{
        private int topo;
        public int[] itens;

        public PilhaLucas(){
            topo = -1;
            itens = new int[50];
        }

        public boolean isEmpty(){
            return (topo == -1);
        }

        public void push(int e){
            topo++;
            itens[topo] = e;
        }

        public int pop(){
            int i = itens[topo];
            topo--;
            return i;
        }
    }
 
}