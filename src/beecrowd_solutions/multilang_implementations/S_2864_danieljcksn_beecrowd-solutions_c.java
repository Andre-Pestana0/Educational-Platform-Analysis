import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (!scanner.hasNextInt()) return;
        int t = scanner.nextInt();

        while (t-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();

            int delta = (int) Math.pow(b, 2) - 4 * a * c;
            double yMax = -((double) delta / (4.0 * a));

            System.out.printf(Locale.US, "%.2f%n", yMax);
        }

        scanner.close();
    }
}