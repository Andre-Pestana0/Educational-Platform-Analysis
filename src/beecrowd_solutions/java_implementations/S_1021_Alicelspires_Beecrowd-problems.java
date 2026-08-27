import java.util.Scanner;

public class Main1021{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        double v = sc.nextDouble();
        int i = 0;
        int[] arrayMoney = {100, 50, 20, 10, 5, 2};
        double[] arrayCents = {100, 50, 25, 10, 5, 1};
        
        System.out.println("NOTAS:");

        while(i < 6){
            double divide = v/arrayMoney[i];
            
            System.out.printf("%d nota(s) de R$ %d.00\n", (int)divide, (int)arrayMoney[i]);
            
            v %= arrayMoney[i];
            i++;
        }
        
        System.out.println("MOEDAS:");
        v = Math.round(v*100);
        int j = 0;
        
        while(j < 6){
            int divide = (int)(v / arrayCents[j]);
            System.out.printf("%d moeda(s) de R$ %.2f\n", divide, arrayCents[j]/100.0);
            
             v -= divide * arrayCents[j];
            j++;
        }
        
		sc.close();
	}
}