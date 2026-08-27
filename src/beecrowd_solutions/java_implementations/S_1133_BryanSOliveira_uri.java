import java.util.Scanner;

public class Uri1133 {
    public static void main (String [] args) {
        Scanner in = new Scanner (System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        in.close();
        if (x > y) {
            int aux = x;
            x = y;
            y = aux;
        }
        for (int i = x +1; i < y; i++) {
            if (i % 5 == 2 | i % 5 == 3) {
                System.out.println(i);
            }
        }
    }
}
