import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();

        String token = scanner.next();
        if (token == null) return;

        int revolucoes = Integer.parseInt(token);

        // Data inicial
        LocalDate dataInicial = LocalDate.of(2020, 12, 21);

        // Cálculo dos dias terrestres para Júpiter
        double anosJupiter = 11.9 * revolucoes;
        int jupiterDias = (int) (365 * anosJupiter + (anosJupiter / 4.0));

        // Cálculo dos dias terrestres para Saturno
        double anosSaturno = 29.6 * revolucoes;
        int saturnoDias = (int) (365 * anosSaturno + (anosSaturno / 4.0));

        // Cálculo da data final adicionando o número exato de dias
        LocalDate dataJupiter = dataInicial.plusDays(jupiterDias);
        LocalDate dataSaturno = dataInicial.plusDays(saturnoDias);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Imprimindo as saídas no formato exato esperado pelo Beecrowd
        System.out.println("Dias terrestres para Jupiter = " + jupiterDias);
        System.out.println("Data terrestre para Jupiter: " + dataJupiter.format(formatter));
        System.out.println("Dias terrestres para Saturno = " + saturnoDias);
        System.out.println("Data terrestre para Saturno: " + dataSaturno.format(formatter));
    }

    static class FastScanner {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }
    }
}