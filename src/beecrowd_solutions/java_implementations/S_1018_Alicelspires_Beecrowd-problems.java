import java.util.Scanner;

public class Main1018{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        double v = sc.nextDouble();
        int i = 0;
        int[] arrayNum = {100, 50, 20, 10, 5, 2, 1};
        
        
        System.out.println((int)v);
        while(i < 7){
            double divide = v/arrayNum[i];
            
            System.out.printf("%d nota(s) de R$ %d,00\n", (int)divide, (int)arrayNum[i]);
            
            v = v%arrayNum[i];
            i++;
        }
		
		sc.close();
	}
}