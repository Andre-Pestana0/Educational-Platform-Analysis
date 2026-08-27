import java.util.Scanner;

public class Main1176 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int valores = 0;
        
        for(int j = 0; j < n; j++){
            valores = sc.nextInt();
            
            if(valores == 0){
                System.out.println("Fib(0) = 0");
            } else if(valores == 1){
                System.out.println("Fib(1) = 1");
            } else {
                long fibonacci = 0, a = 0, b = 1;
                for(int i = 1; i < valores;i++){
                    fibonacci = a + b;
                    a = b;
                    b = fibonacci;
                }
                System.out.println("Fib("+valores+") = "+fibonacci);
            }
        }
    }
}
