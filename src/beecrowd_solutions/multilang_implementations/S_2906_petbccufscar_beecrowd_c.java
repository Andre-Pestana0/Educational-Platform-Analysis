import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static class No {
        String email;
        No esq;
        No dir;

        public No(String email) {
            this.email = email;
            this.esq = null;
            this.dir = null;
        }
    }

    static int emailsUnicos = 0;

    static String limpa(String original) {
        StringBuilder aux = new StringBuilder();
        int i = 0;

        while (i < original.length()) {
            char c = original.charAt(i);

            if (c == '+') {
                break;
            } else if (c == '.') {
                i++;
            } else {
                aux.append(c);
                i++;
            }
        }

        return aux.toString();
    }

    static No adiciona(No ramo, String nome) {
        if (ramo == null) {
            ramo = new No(nome);
            emailsUnicos++;
        } else if (ramo.email.compareTo(nome) > 0) {
            ramo.esq = adiciona(ramo.esq, nome);
        } else if (ramo.email.compareTo(nome) < 0) {
            ramo.dir = adiciona(ramo.dir, nome);
        }

        return ramo;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String line = reader.readLine();
        if (line == null) return;
        
        int n = Integer.parseInt(line.trim());
        No arvore = null;

        while (n-- > 0) {
            String email = reader.readLine().trim();
            
            // Separa usuário e domínio pelo caractere '@'
            int atPos = email.indexOf('@');
            if (atPos != -1) {
                String usuario = email.substring(0, atPos);
                String dominio = email.substring(atPos); // Inclui o '@'

                String usuarioLimpo = limpa(usuario);
                String emailFinal = usuarioLimpo + dominio;

                arvore = adiciona(arvore, emailFinal);
            }
        }

        System.out.println(emailsUnicos);
    }
}