import java.io.IOException;
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) throws IOException { 
        Scanner entrada = new Scanner(System.in);
        int s[] = new int[10010];
        int n, k, c, f, flag, pos;

        while(true) {
            String expressao = entrada.nextLine();

            String[] nMotCar = expressao.split(" ");
            n = Integer.parseInt(nMotCar[0]);
            k = Integer.parseInt(nMotCar[1]);

            if(n == 0 && k == 0) {
                break;
            }

            flag = 0;
            pos = -1;

            for(int i = 0; i < n; i++) {
                expressao = entrada.nextLine();

                String[] horaChegadaSaida = expressao.split(" ");
                c = Integer.parseInt(horaChegadaSaida[0]);
                f = Integer.parseInt(horaChegadaSaida[1]);

                if(flag == 0) {
                    if(pos == -1) {
                        pos++;
                        s[pos] = f;
                    } else {
                        int ultimo = s[pos];
                        if (c >= ultimo) {
                            while(pos >= 0 && c >= ultimo) {
                                pos--;
                                if (pos < 0) break;
                                ultimo = s[pos];
                            }
                        }
                        // Check if stack size after popping exceeds K-1
                        if (pos + 1 >= k) {
                            flag = 1;
                        } else {
                            if (pos >= 0  && f > s[pos]){
                                flag = 1;
                            } else {
                                pos++;
                                s[pos] = f;
                            }
                        }
                    }
                }
            }
            if(flag == 0) {
                System.out.println("Sim");
            } else {
                System.out.println("Nao");
            }
        }
        entrada.close();
    }
}
