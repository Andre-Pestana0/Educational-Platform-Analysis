import java.util.Scanner;

public class URI3163 {

    public static void main(String[] args){

        FilaLucas norte = new FilaLucas();
        FilaLucas sul = new FilaLucas();
        FilaLucas leste = new FilaLucas();
        FilaLucas oeste = new FilaLucas();
        FilaLucas currentFilaLucas = new FilaLucas();
        boolean shouldKeep = true;
        StringBuilder result = new StringBuilder();

        Scanner entrada = new Scanner(System.in);

        while (entrada.hasNext() && shouldKeep){
            String input = entrada.nextLine();

            switch (input) {
                case "0":
                    shouldKeep = false;
                    break;
                case "-1":  
                    currentFilaLucas = oeste;
                    break;
                case "-2":  
                    currentFilaLucas = sul;
                    break;
                case "-3":  
                    currentFilaLucas = norte;
                    break;
                case "-4":  
                    currentFilaLucas = leste;
                    break;

                default:
                    currentFilaLucas.inserir(input);
                    break;
            }
        }
        //leste norte sul oeste
        
        while(!(leste.isEmpty() && norte.isEmpty() && sul.isEmpty() && oeste.isEmpty())){
            if(!oeste.isEmpty()){
                result.append(oeste.retirar() + " ");
            }
            if(!norte.isEmpty()){
                result.append(norte.retirar() + " ");
            }
            if(!sul.isEmpty()){
                result.append(sul.retirar() + " ");
            }
            if(!leste.isEmpty()){
                result.append(leste.retirar() + " ");
            }
        } 

        System.out.println(result.toString().trim());
    }


    private static class FilaLucas {

        private int total;
        private int inicio;
        private int fim;
        private String[] valores;
        static int size = 1000;
    
        public FilaLucas(){
            valores = new String[size];
            inicio = 0;
            fim = 0;
            total = 0;
        }
    
        public void inserir(String elemento){
            valores[fim] = elemento;
            fim = (fim + 1) % size;
            total++;
        }

        public String retirar(){
            String elemento = valores[inicio];
            inicio = (inicio + 1) % size;
            total--;
            return elemento;
        }

        public boolean isEmpty(){
            return (total == 0);
        }
    
        public boolean isFull(){
            return (total == size);
        }
     }
    
}