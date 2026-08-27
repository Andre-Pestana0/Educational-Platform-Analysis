import java.util.Scanner;
import java.util.Arrays;

public class URI2137 {
 
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        
        while (entrada.hasNext()){
            int n = Integer.parseInt(entrada.nextLine()), i, k;
            String[] valores = new String[n];
            for (k=0; k<n; k++){
                String valor = entrada.nextLine();
                int valorInt = Integer.parseInt(valor);
                if (k==0){
                    valores[k] = valor;
                }
                else{
                    for (i=k-1; i>=0 && Integer.parseInt(valores[i]) > valorInt; i--){
                        valores[i+1] = valores[i];
                    }
                    valores[i+1] = valor;
                }
            }
            Arrays.stream(valores).forEach(System.out::println);
        }
    }
        
        
}