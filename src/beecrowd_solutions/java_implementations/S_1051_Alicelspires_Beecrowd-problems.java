import java.util.Scanner;

public class Main1051
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        double salario = sc.nextDouble();
        double valor = 0;
        
        if(salario > 4500.00){
            valor = (1000.00*0.08)+(1500.00*0.18)+((salario-4500)*0.28);
            System.out.printf("R$ %.2f\n", valor);
        }    
        else if(salario > 3000.00){
            valor = (1000.00*0.08)+((salario-3000.00)*0.18);
            System.out.printf("R$ %.2f\n", valor);
        }
        else if(salario > 2000.00){
            valor = (salario - 2000.00)*0.08;
            System.out.printf("R$ %.2f\n", valor);
        } 
        else if(salario >= 0){
                System.out.println("Isento");
            }
    }
}