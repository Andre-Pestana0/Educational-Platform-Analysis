import java.util.Scanner;

public class Uri1172 {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int [] valores = new int [10];
        
        for (var i = 0; i <= 9; i++) {
            valores[i] = in.nextInt();
            if (valores[i] <= 0) {
                valores[i] = 1;
            }
            System.out.println("X[" + i + "] = " + valores[i]);
        }
        
        in.close();
    
    }
}
