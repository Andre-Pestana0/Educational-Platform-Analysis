import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Funções de operações bitwise e aritméticas (retornam o valor atualizado)
    public static int add(int r, int v) {
        return (r + v) % 256;
    }

    public static int sub(int r, int v) {
        return (r - v + 256) % 256;
    }

    public static int mul(int r, int v) {
        return (r * v) % 256;
    }

    public static int div(int r, int v) {
        return (r / v) % 256;
    }

    public static int and(int r, int v) {
        return (r & v) % 256;
    }

    public static int or(int r, int v) {
        return (r | v) % 256;
    }

    public static int xor(int r, int v) {
        return (r ^ v) % 256;
    }

    // Classe auxiliar para armazenar a instrução e seus argumentos
    static class Instruction {
        String op;
        List<Integer> args;

        Instruction(String op) {
            this.op = op;
            this.args = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int n = Integer.parseInt(st.nextToken());
            Instruction[] instructions = new Instruction[n];

            for (int i = 0; i < n; i++) {
                while (!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                
                // Lê o índice (idx) e a operação (op)
                st.nextToken(); 
                String op = st.nextToken();

                instructions[i] = new Instruction(op);

                if (op.equals("halt")) {
                    continue;
                }

                if (op.equals("gotoif")) {
                    while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
                    int v = Integer.parseInt(st.nextToken());

                    while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
                    int instructionTarget = Integer.parseInt(st.nextToken());

                    instructions[i].args.add(v);
                    instructions[i].args.add(instructionTarget - 1);
                } else {
                    while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
                    int v = Integer.parseInt(st.nextToken());

                    instructions[i].args.add(v);
                }
            }

            int currentValue = 0;
            int it = 0;

            for (int i = 0; i < n; i++) {
                it++;
                if (it > 100000) {
                    break;
                }

                Instruction inst = instructions[i];
                String op = inst.op;

                if (op.equals("halt")) {
                    System.out.println(currentValue);
                    break;
                }

                int v = inst.args.get(0);

                if (op.equals("gotoif")) {
                    if (currentValue >= v) {
                        i = inst.args.get(1) - 1;
                    }
                } else if (op.equals("add")) {
                    currentValue = add(currentValue, v);
                } else if (op.equals("sub")) {
                    currentValue = sub(currentValue, v);
                } else if (op.equals("mul")) {
                    currentValue = mul(currentValue, v);
                } else if (op.equals("div")) {
                    currentValue = div(currentValue, v);
                } else if (op.equals("or")) {
                    currentValue = or(currentValue, v);
                } else if (op.equals("xor")) {
                    currentValue = xor(currentValue, v);
                } else if (op.equals("and")) {
                    currentValue = and(currentValue, v);
                }
            }

            if (it > 100000) {
                System.out.println("execucao infinita");
            }
        }
    }
}