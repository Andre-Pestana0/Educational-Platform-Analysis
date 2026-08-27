import java.util.Scanner;

public class Main1151 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = 0, b = 1;
        
        System.out.print(a);
        for(int i = 1; i < n; i++) {
            System.out.print(" " + b);
            int fibonacci = a + b;
            a = b;
            b = fibonacci;
        }
        System.out.println();
    }
}
