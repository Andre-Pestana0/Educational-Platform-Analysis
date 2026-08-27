import java.util.Scanner;

public class Main1048
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        
        double salario = sc.nextDouble();
        
        double ganho = 0;
        double valor = 0;
        double percent = 0;
        
        if(salario >= 0 && salario <= 400.00){
            percent = 0.15;
            valor = salario*percent;
            ganho = valor;
            salario += valor;
        } else 
        if(salario > 400.00 && salario <= 800.00){
            percent = 0.12;
            valor = salario*percent;
            ganho = valor;
            salario += valor;
        } else
        if(salario > 800  && salario <= 1200.00){
            percent = 0.10;
            valor = salario*percent;
            ganho = valor;
            salario += valor;
        } else
        if(salario > 1200.00 && salario <= 2000.00){
            percent = 0.07;
            valor = salario*percent;
            ganho = valor;
            salario += valor;
        }
        else{
            percent = 0.04;
            valor = salario*percent;
            ganho = valor;
            salario += valor;
        }
        
        System.out.printf("Novo salario: %.2f\n", salario);
        System.out.printf("Reajuste ganho: %.2f\n", ganho);
        System.out.println("Em percentual: "+ (int)(percent*100) +" %");

	}
}