import java.io.IOException;
import java.util.Scanner;
public class beecrowd1063 {
    public static void main(String[] args) throws IOException {
        Scanner Sc = new Scanner(System.in);
        ordem_vagoes B = new ordem_vagoes();
        while (Sc.hasNext()) {
            int n_vag = Sc.nextInt();
            if (n_vag == 0) {
                break;
            }
            while (true) {
                String[] j = new String[n_vag];
                for(int i = 0;i<n_vag;i++){
                    j[i] = Sc.next();
                }
                int J = 0;
                int parada_1 = 0;
                String[] n_n_vag = new String[n_vag];
                for (int i = 0; i < n_vag; i++) {
                    n_n_vag[i] = Sc.next();
                }
                int interador = 0;
                while (true) {
                    if (interador >= n_vag) {
                        break;
                    }
                    if (parada_1 == 1) {
                        break;
                    }
                    while (true) {
                        if (!B.isEmpty() && B.peek().equals(n_n_vag[interador])) {
                            System.out.print("R");
                            B.pop();
                            break;
                        } else if (J < n_vag) {
                            System.out.print("I");
                            B.push(j[J]);
                            J++;
                            if (B.peek().equals(n_n_vag[interador])) {
                                System.out.print("R");
                                B.pop();
                                break;
                            }

                        } else {
                            parada_1 = 1;
                            break;
                        }
                    }
                    interador++;

                }
                if (B.isEmpty()) {
                    System.out.println();
                } else {
                    System.out.println(" Impossible");
                }
                B.clear();
                break;
            }
        }
    }
}
class beecrowd1063 {
    private no_D head;
    private no_D tail;
    private int size;

    public void push(String elemento) {
        no_D novo_No = new no_D(elemento);
        if (this.tail == null) {
            this.head = novo_No;
        } else {
            this.tail.setProximo(novo_No);
            novo_No.setAnterior(this.tail);
        }
        this.tail = novo_No;
        this.size++;
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
        return tail.getElemento();
    }
    public void clear() {
        this.size =0;
        this.head = null;
        this.tail = null;
    }
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }
}
class beecrowd1063 {
    private no_D anterior;
    private String elemento;
    private no_D proximo;

    public no_D(String elemento) {
        this.anterior=null;
        this.elemento = elemento;
        this.proximo=null;
    }

    public no_D() {}

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
