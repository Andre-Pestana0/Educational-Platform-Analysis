import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            int t = sc.nextInt();

            while (t-- > 0) {
                int n = sc.nextInt();
                int gap = 2;
                int regioes = 2;

                for (int i = 1; i < n; i++) {
                    regioes += gap;
                    gap++;
                }

                System.out.println(regioes);
            }
        }

        sc.close();
    }
}