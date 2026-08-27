import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            int t = sc.nextInt();

            while (t-- > 0) {
                int n = sc.nextInt();

                // 2^n - 1 usando deslocamento de bits (bit shift)
                int result = (1 << n) - 1;

                System.out.println(result);
            }
        }

        sc.close();
    }
}