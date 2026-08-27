import java.util.Scanner;

public class URI1259 {
 
    public static void main(String[] args){
        int i,k;
        Scanner entrada = new Scanner(System.in);
        int n = Integer.parseInt(entrada.nextLine());
        int tamanho = 0;
        int[] numbers = new int[n];

        for (k=0; k<n; k++){
            int valor = Integer.parseInt(entrada.nextLine());
            if (tamanho == 0){
                numbers[0] = valor;
            }
            else{
                for (i=tamanho-1; i>=0 && numbers[i] > valor; i--){
                    numbers[i+1] = numbers[i];
                }
                numbers[i+1] = valor;
            }
            tamanho++;
        }

        printPares(numbers);
        printImpares(numbers);
    
    }

    private static boolean isEven(int n){
        return ((n % 2 == 0)); 
    }

    private static void printPares(int[] numbers){
        for(int x = 0; x < numbers.length; x++){
            int number = numbers[x];
            if (isEven(number)){
                System.out.println(number);
            }
        }
    }
    private static void printImpares(int[] numbers){
        for(int x = numbers.length-1; x >= 0; x--){
            int number = numbers[x];
            if (!isEven(number)){
                System.out.println(number);
            }
        }
    }
}