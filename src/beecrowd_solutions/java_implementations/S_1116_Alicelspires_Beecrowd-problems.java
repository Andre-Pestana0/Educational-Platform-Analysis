import java.util.Scanner;

public class Main1116 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int linhas = sc.nextInt();
        
        for(int i = 0; i < linhas; i++){
            double n1 = sc.nextDouble();
            double n2 = sc.nextDouble();
            if(n2 == 0){
                System.out.println("divisao impossivel");
            } else {
                System.out.printf("%.1f\n", n1/n2);
            }
        }
        
    }
}
