import java.util.Scanner;

public class Uri1175 {
    public static void main(String[] args) {
        
        // ENTRADA
        Scanner in = new Scanner(System.in);
        int [] num = new int [20];
        for (var i = 0; i < num.length; i++) {
            num[i] = in.nextInt(); 
        } 

        // PROCESSAMENTO
        for (var i = 0; i <= 9; i++) {
            int aux = num[i];
            num[i] = num[19 - i];
            num[19 - i] = aux;

            // SAÍDA
        }
        
        for (var i = 0; i < 19; i++) {
            System.out.println("N[" + i + "] = " + num[19 - 1]);
        }
        in.close();
        
    }
}
