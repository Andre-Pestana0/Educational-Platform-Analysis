import java.util.Scanner;

public class beecrowd2479 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaEncadeadaOrdenada<String> nomes = new ListaEncadeadaOrdenada<>();
        int comportaram = 0;
        int n;

        do n = sc.nextInt(); while (n < 0 || n > 100);

        for (int i = 0; i < n; i++) {
            String comporta;
            do comporta = sc.next(); while (!comporta.equals("+") && !comporta.equals("-"));

            String nome = sc.next();
            nomes.add(nome);

            if (comporta.equals("+")) comportaram++;
        }

        for (int i = 0; i < nomes.size(); i++) System.out.println(nomes.get(i));
        System.out.println("Se comportaram: " + comportaram + " | Nao se comportaram: " + (nomes.size() - comportaram));
    }
}

class beecrowd2479<E extends Comparable<E>> {
    private Node<E> primeiro;
    private Node<E> ultimo;
    private int count = 0;

    public ListaEncadeadaOrdenada() {
        clear();
    }

    public E get(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException("Ãndice " + index + ", Comprimento " + count);

        Node<E> atual = primeiro;
        for (int i = 0; i < count; i++) {
            if (i == index) return atual.item;
            atual = atual.proximo;
        }
        return null;
    }

    public int size() {
        return count;
    }

    public void clear() {
        primeiro = null;
        ultimo = null;
        count = 0;
    }

    public boolean add(E element) {
        Node<E> novo = new Node<>(element);
        if (contains(novo.item)) {
            return false;
        }

        if (primeiro == null) {
            primeiro = novo;
            ultimo = novo;
            count++;
        } else if (novo.item.compareTo(primeiro.item) < 0) {
            novo.proximo = primeiro;
            primeiro.anterior = novo;
            primeiro = novo;
            count++;
        } else if (novo.item.compareTo(ultimo.item) > 0) {
            ultimo.proximo = novo;
            novo.anterior = ultimo;
            ultimo = novo;
            count++;
        } else {
            Node<E> aux = primeiro;

            while (aux != null && ((novo.item.compareTo(aux.item)) >= 0)) aux = aux.proximo;

            novo.anterior = aux.anterior;
            aux.anterior.proximo = novo;
            novo.proximo = aux;
            aux.anterior = novo;
            count++;
        }

        return true;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public void remove(int index) {
        if (index == 0) {
            if (count > 1) {
                primeiro.proximo.anterior = null;
                primeiro = primeiro.proximo;
                count--;
            } else clear();
        } else if (index == count - 1) {
            ultimo.anterior.proximo = null;
            ultimo = ultimo.anterior;
            count--;
        } else {
            int idx = 0;
            for (Node<E> x = primeiro; x != null; x = x.proximo) {
                if (idx == index) {
                    x.proximo.anterior = x.anterior;
                    x.anterior.proximo = x.proximo;
                    count--;
                    return;
                }
                idx++;
            }
        }
    }

    public int indexOf(Object o) {
        int index = 0;
        for (Node<E> x = primeiro; x != null; x = x.proximo) {
            if (o.equals(x.item)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public String toString() {
        Node<E> atual = primeiro;
        String str = "[";
        while (atual != null) {
            str += (atual.proximo == null) ? atual.item : atual.item + ", ";
            atual = atual.proximo;
        }
        return str + "]";
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private static class beecrowd2479<E> {
        E item;
        Node<E> anterior;
        Node<E> proximo;

        Node(E item) {
            this.item = item;
        }
    }
}
