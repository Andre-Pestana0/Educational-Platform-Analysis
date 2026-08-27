import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int b = scanner.nextInt();
            int t = scanner.nextInt();

            // A metade da área da nota original vale a área total / 2:
            int areaTotal = 70 * 160;
            double metade = areaTotal / 2.0;

            // Sabemos que os cortes formarão dois trapézios. Basta calcular a área destes e verificar as condições.
            // 70 é a altura, em cm, da nota.
            double pedacoFelix = ((b + t) * 70) / 2.0;
            double pedacoMarzia = areaTotal - pedacoFelix;

            // Caso os dois pedaços sejam iguais a metade da área original, a nota torna-se inválida.
            if (pedacoFelix == metade) {
                System.out.println("0");
                scanner.close();
                return;
            }

            // Verifica quem ficou com o pedaço valendo R$ 100,00
            if (pedacoFelix > pedacoMarzia) {
                System.out.println("1");
            } else {
                System.out.println("2");
            }
        }

        scanner.close();
    }
}