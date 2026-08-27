import java.util.Scanner;

public class Main1050
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        
        int num = sc.nextInt();
        int[] ddd = {61, 71, 11, 21, 32, 19, 27, 31};
        String[] desti = {"Brasilia", "Salvador", "Sao Paulo", "Rio de Janeiro", "Juiz de Fora", "Campinas", "Vitoria", "Belo Horizonte"};
        String estado = null;
        
        for(int i = 0; i < 8; i++){
            if(num == ddd[i]){
                estado = desti[i];
            } 
        }
        if(estado == null){
            System.out.println("DDD nao cadastrado");
        } else {
            System.out.println(estado);
        }

	}
}