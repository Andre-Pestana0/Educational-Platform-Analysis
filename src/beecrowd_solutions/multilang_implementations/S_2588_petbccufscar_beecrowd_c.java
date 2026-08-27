import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    // Ordenação (Insertion Sort) mantendo exatamente a lógica do seu código original
    public static void ordenaSequencia(char[] sequencia) {
        char aux;
        for (int i = 0; i + 1 < sequencia.length; i++) {
            for (int j = i + 1; j - 1 >= 0 && sequencia[j] <= sequencia[j - 1]; j--) {
                aux = sequencia[j];
                sequencia[j] = sequencia[j - 1];
                sequencia[j - 1] = aux;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // Transforma a String em um array de caracteres manipulável
            char[] sequencia = line.toCharArray();

            ordenaSequencia(sequencia);

            int par = 0;
            int caracteresFaltantes = 0;
            char aux = sequencia[0];

            for (int i = 0; i < sequencia.length; i++) {
                if (sequencia[i] != aux) {
                    caracteresFaltantes += par;
                    par = 0;
                    aux = sequencia[i];
                }
                if (par == 1) {
                    par--;
                } else if (par == 0) {
                    par++;
                }
            }

            caracteresFaltantes += par;

            if (caracteresFaltantes != 0) {
                System.out.println(caracteresFaltantes - 1);
            } else {
                System.out.println(caracteresFaltantes);
            }
        }
    }
}