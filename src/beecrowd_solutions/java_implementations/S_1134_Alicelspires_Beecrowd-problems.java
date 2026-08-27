import java.util.Scanner;

public class Main1134 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int alcool = 0;
        int gasolina = 0;
        int diesel = 0;
        int n = sc.nextInt();
        
        while (n != 4){
            n = sc.nextInt();
            while( n > 4 || n < 1){
                n = sc.nextInt();
            }
            switch (n){
                case 1:
                    alcool++;
                    break;
                case 2:
                    gasolina++;
                    break;
                case 3:
                    diesel++;
                    break;
            }
        }
        System.out.println("MUITO OBRIGADO");
        System.out.println("Alcool: "+ alcool);
        System.out.println("Gasolina: "+ gasolina);
        System.out.println("Diesel: "+ diesel);
    }
}
