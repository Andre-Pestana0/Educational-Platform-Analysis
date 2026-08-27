import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// g-otn
// URI_2590
// Sete

public class URI_2590 {

    public static void main(String[] args) throws IOException {

        // Cheat sheet / testing
//        for (int i = 0; i < 50; i++) {
//            System.out.println("i: " + i + "\timod7: " + (i % 7) + "\ti/7: " + (i/7) + "\tteste: " + (i%8) + "\túltimo: " + Long.valueOf((long)Math.pow(7, i)).toString().charAt(Long.valueOf((long)Math.pow(7, i)).toString().length() - 1) + "\t7^i: " + (long) (Math.pow(7, i)));
//        }

        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

        int numeroDeInstancias = Integer.parseInt(leitor.readLine());

        for (int i = 0; i < numeroDeInstancias; i++) {

            int expoente = Integer.parseInt(leitor.readLine()); // Integer.MAX_VALUE > 10^9
            int fatiaDaPizza = expoente % 8;

            switch (fatiaDaPizza) {
                case 0:
                case 4:
                    System.out.println('1');
                    break;
                case 1:
                case 5:
                    System.out.println('7');
                    break;
                case 2:
                case 6:
                    System.out.println('9');
                    break;
                case 3:
                case 7:
                    System.out.println('3');
                    break;
            }
        }

    }

}
