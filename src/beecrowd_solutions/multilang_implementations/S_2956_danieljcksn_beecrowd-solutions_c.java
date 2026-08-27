import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Define a localização para EUA para garantir o uso de ponto decimal
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextDouble()) {
            double b = scanner.nextDouble();
            double h = scanner.nextDouble();

            double area = (b * h) / 2.0;

            System.out.printf("Concluimos que, dado o limite da entrada, a resposta seria:  y = f(x) = %.5f.\n", area);
        }

        scanner.close();
    }
}