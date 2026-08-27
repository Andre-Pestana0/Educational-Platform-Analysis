import java.util.Scanner;

public class Main1079 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            double n1 = sc.nextDouble();
            double n2 = sc.nextDouble();
            double n3 = sc.nextDouble();
            
            double mediaPonderada = ((n1*2)+(n2*3)+(n3*5))/(2+3+5);
            System.out.printf("%.1f\n",mediaPonderada);
        }
        
    }
}
