import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String linhaEntrada;

        while ((linhaEntrada = reader.readLine()) != null) {
            String[] partes = linhaEntrada.trim().split("\\s+");
            if (partes.length < 2) continue;

            int n = Integer.parseInt(partes[0]);
            int d = Integer.parseInt(partes[1]);

            // Condição de parada [0, 0]
            if (n == 0 && d == 0) {
                break;
            }

            String linha = reader.readLine().trim();
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < linha.length(); i++) {
                char atual = linha.charAt(i);

                // Enquanto puder remover números (d > 0) e o topo da pilha for menor que o número atual
                while (d > 0 && !stack.isEmpty() && stack.peek() < atual) {
                    stack.pop();
                    d--;
                }
                stack.push(atual);
            }

            // Se ainda sobrarem remoções pendentes, removemos do final
            while (d > 0 && !stack.isEmpty()) {
                stack.pop();
                d--;
            }

            // Constrói o resultado a partir da pilha
            StringBuilder resultado = new StringBuilder();
            for (char c : stack) {
                resultado.append(c);
            }

            System.out.println(resultado.toString());
        }
    }
}