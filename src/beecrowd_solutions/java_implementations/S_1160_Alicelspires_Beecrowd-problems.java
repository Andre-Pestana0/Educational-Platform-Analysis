import java.util.Scanner;

public class Main1160 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        for(int i = 0; i < T; i++){
            int PA = sc.nextInt();
            int PB = sc.nextInt();
            double G1 = sc.nextDouble();
            double G2 = sc.nextDouble();

            int anos = 0;
            
            /* Juros composto, se soma ao valor atual o valor anterior
               vezes a taxa aplicada. 
            */
            while(PA <= PB && anos <= 100){ 
                PA += PA*(G1/100.0);
                PB += PB*(G2/100.0);
                
                anos++;
            }
            
            if(anos > 100){
                    System.out.println("Mais de 1 seculo.");
                } else {
                    System.out.println(anos + " anos.");
                }
        }
        
    }
}
