import java.util.Scanner;

public class URI1257 {
 
    public static void main(String[] args){
        String abcdary = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Scanner entrada = new Scanner(System.in);
        int n = Integer.parseInt(entrada.nextLine());

        for (int k=0; k<n; k++){
            int elementsTotal = Integer.parseInt(entrada.nextLine());
            int sum = 0;
            for(int element = 0; element < elementsTotal; element++){
                String phrase = entrada.nextLine();
                for (int position =0; position < phrase.length(); position++){
                    // String c = Character.toString(phrase.charAt(position));
                    char lower = Character.toLowerCase(phrase.charAt(position)); 
                    int charIndex = lower - 'a';
                    sum += (charIndex + position + element);
                }
            }
            System.out.println(sum);
        }       
    }

    // private static class Registro{
    //     private String key;
    //     private int value;

    //     public Registro(){

    //     }

    //     public Registro(String key, int value){
    //         this.key = key;
    //         this.value = value;
    //     }

    //     public setKey(String key){
    //         this.key = key;
    //     }

    //     public int getValue(){
    //         return this.value;
    //     }

    //     public int hashCode(){
    //         return value % 26;
    //     }
    // }

    // private static class MapaHash{
    //     private Registro valores[];

    //     public MapaHash(){
    //         valores = new Registro[26];
    //     }

    //     public void put(Registro registro){
    //         int posicao = registro.value;
    //         if (valores[posicao] == null){
    //             valores[posicao] = registro;
    //         }
    //     }

    //     public Registro get(String key){
    //         Registro r = new Registro();
    //         r.setKey(key);
    //         int posicao = r.hashCode();
    //         return valores[posicao];
    //     }
    // }
}