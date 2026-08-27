import java.io.IOException;
import java.util.Scanner;

public class beecrowd1083 {
    
    static int contado = 0;
    static int contadoParent = 0;
    static boolean parada2 = false;
 
    public static void main(String[] args) throws IOException {
 
        Scanner Sc = new Scanner(System.in);
        while (Sc.hasNext()) {
            in_pos variables = new in_pos();
            in_pos signals = new in_pos();
            int parada1 = 0;
            String[] expression = Sc.nextLine().split("");
            for (int j = 0; j < expression.length; j++) {
                String oscillating = expression[j];
                if (!deu_math(oscillating)) {
                    parada1++;
                    break;
                } else {
                    inpos(oscillating, expression.length, j, variables, signals);
                    if (parada2 ) {
                        break;
                    }
                }
            }
            if (parada1 == 1) {
                System.out.println("Lexical Error!");
                contadoParent = 0;
                contado = 0;

            } else if (parada2 || contadoParent != 0) {
                System.out.println("Syntax Error!");
                contado = 0;
                parada2 = false;
                contadoParent = 0;

            } else {
                variables.print();
                contadoParent = 0;
                contado = 0;

            }
        }
    }

    public static int importance(String X) {
        switch (X) {
            case "^":
                return 6;
            case "/":
            case "*":
                return 5;
            case "+":
            case "-":
                return
                        4;
            case ">":
            case "<":
            case "=":
            case "#":
                return 3;
            case ".":
                return 2;
            case "|":
                return 1;
            default:
                return 0;
        }
    }

    public static boolean deu_math(String comp) {
        String[] a = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z",
                "+", "-", "*", "/", "^", "<", ">", "=", "#", ".", "|",
                "(", ")",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < a.length; i++) {
            if (comp.equalsIgnoreCase(a[i])) {
                return true;
            }
        }
        return false;
    }

    public static void inpos(String oscillating, int expression, int j, in_pos variables, in_pos signals) {
        if (oscillating.equals("(") || oscillating.equals(")")) {
            signals.push(oscillating);
            if (j == 0 && oscillating.equals(")")) {
                contadoParent++;
            } else if (oscillating.equals(")")) {
                contadoParent--;
            } else {
                contadoParent++;
            }

        } else if (oscillating.equals("+") || oscillating.equals("-") || oscillating.equals("*") || oscillating.equals("/") || oscillating.equals("^")
        || oscillating.equals("<") || oscillating.equals(">") || oscillating.equals("=") || oscillating.equals("#") || oscillating.equals(".") || oscillating.equals("|")) {
            if (!signals.isEmpty() && importance(oscillating) <= importance(signals.peek())) {
                while (!signals.isEmpty() && importance(oscillating) <= importance(signals.peek())) {
                    String var1 = variables.peek();
                    variables.pop();
                    String var = variables.peek();
                    variables.pop();
                    String var2 = signals.peek();
                    signals.pop();
                    String concat = var + var1 + var2;
                    variables.push(concat);
                }
                signals.push(oscillating);
                if(j==0){
                    parada2 = true;
                }
                contado--;
                if (contado == -1) {
                    parada2 = true;
                }

            } else {
                signals.push(oscillating);
                contado--;
                if (contado == -1) {
                    parada2 = true;
                }
            }
        } else {
            variables.push(oscillating);
            contado++;
            if (contado == 2) {
                parada2 = true;
            }

        }
        if (!signals.isEmpty() && oscillating.equals(")")) {
            signals.pop();
            while (!signals.isEmpty() && !signals.peek().equals("(")) {
                String var1 = variables.peek();
                variables.pop();
                String var = variables.peek();
                variables.pop();
                String var2 = signals.peek();
                signals.pop();
                String concat = var + var1 + var2;
                variables.push(concat);
            }
            signals.pop();
        }
        if (!signals.isEmpty() && j == expression - 1) {
            while (!signals.isEmpty()) {
                String var1 = variables.peek();
                variables.pop();
                String var = variables.peek();
                variables.pop();
                String var2 = signals.peek();
                signals.pop();
                String concat = var + var1 + var2;
                variables.push(concat);
            }
        }
    }
}

class beecrowd1083 {
    private no_D head;
    private no_D tail;
    private int size;

    public void push(String elemento) {
        no_D novo_No = new no_D(elemento);
        if (this.head == null) {
            this.head = novo_No;
        } else {
            this.tail.setProximo(novo_No);
            novo_No.setAnterior(this.tail);
        }
        this.tail = novo_No;
        size++;
    }
    public void pop() {
        if (tail != null) {
            if (head == tail) {
                this.head = null;
                this.tail = null;
            } else {
                no_D no_atual = tail.getAnterior();
                this.tail = no_atual;
                this.tail.setProximo(null);
            }
        }
        size--;
    }
    public String peek() {
        if (tail != null) {
            return tail.getElemento();
        }
        return null;
    }
    public int getSize(){
        return this.size;
    }
    public void clear(){
        this.head=null;
        this.tail=null;
    }

    public void print() {
        no_D no_atual = head;
        while (no_atual != null) {
            if (no_atual.getProximo() != null) {
                System.out.print(no_atual.getElemento());
            } else {
                System.out.println(no_atual.getElemento());
            }
            no_atual = no_atual.getProximo();
        }
        this.clear();
    }
    public boolean isEmpty() {
        return size == 0;
    }
}
class beecrowd1083 {
    private no_D anterior;
    private String elemento;
    private no_D proximo;

    public no_D() {}

    public no_D(String elemento) {
        this.anterior = null;
        this.elemento = elemento;
        this.proximo = null;
    }

    public no_D getAnterior() {
        return anterior;
    }

    public void setAnterior(no_D anterior) {
        this.anterior = anterior;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public no_D getProximo() {
        return proximo;
    }

    public void setProximo(no_D proximo) {
        this.proximo = proximo;
    }
}
