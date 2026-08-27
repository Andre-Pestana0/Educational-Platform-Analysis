import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static long power(long a) {
        long result = 1;

        while (a > 0) {
            result *= 2;
            a--;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        long n = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        /*
         Sendo n arquivos distintos, o programa
         de compressão deve ser capaz de gerar, pelo menos
         n arquivos distintos. A pergunta, portanto, é: 
         Quantos arquivos únicos de tamanho máximo b bits 
         podem ser obtidos?
         
         Os arquivos comprimidos terão
         tamanho máximo de 0,...,b bits.
         Sendo arquivos binários, para b bits, teremos
         2^b arquivos distintos, 
         2^(b-1) arquivos distintos para b-1 bits,
         ...
         
         Se o somatório de 2^0, 2^1, ... 2^b for 
         maior ou igual a n, saberemos que
         será possível gerar uma quantidade de arquivos distintos
         maior ou igual à quantidade dos arquivos de entrada.
         
         Caso contrário, não será possível gerar, pelo menos
         n arquivos distintos com arquivos comprimidos contendo, 
         no máximo, b bits.
        */

        if ((power(b + 1) - 1) >= n) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}