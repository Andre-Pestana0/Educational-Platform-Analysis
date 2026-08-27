import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }

        int n = sc.nextInt();
        int[][] canhota = new int[n + 1][2];

        for (int i = 1; i <= n; ++i) {
            int node = sc.nextInt();
            canhota[node][0] = sc.nextInt();
            canhota[node][1] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[][] destra = new int[m + 1][2];

        for (int i = 1; i <= m; ++i) {
            int node = sc.nextInt();
            destra[node][0] = sc.nextInt();
            destra[node][1] = sc.nextInt();
        }

        int seqE = 1;
        int seqD = 1;

        // Sequência de vértices centrais partindo dos nós (Canhota)
        for (int i = 2; i <= n; ++i) {
            int seqlocal = 1;
            int cnode = canhota[i][1];

            while (cnode != 0) {
                seqlocal++;
                cnode = canhota[cnode][1];
            }

            seqE = Math.max(seqE, seqlocal);
        }

        // Sequência de vértices centrais partindo da raiz da árvore canhota
        int seqRaizE = 1;
        int cnode = canhota[1][1];
        while (cnode != 0) {
            seqRaizE++;
            cnode = canhota[cnode][1];
        }

        // Sequência de vértices centrais partindo da raiz da árvore destra
        int seqRaizD = 1;
        cnode = destra[1][0];
        while (cnode != 0) {
            seqRaizD++;
            cnode = destra[cnode][0];
        }

        // Sequência de vértices centrais partindo dos nós (Destra)
        for (int i = 2; i <= m; ++i) {
            int seqlocal = 1;
            cnode = destra[i][0];

            while (cnode != 0) {
                seqlocal++;
                cnode = destra[cnode][0];
            }

            seqD = Math.max(seqD, seqlocal);
        }

        int op1 = Math.min(seqRaizE, seqD);
        int op2 = Math.min(seqRaizD, seqE);
        int op3 = Math.min(seqRaizE, seqRaizD);

        int resultado = m + n - Math.max(Math.max(op1, op2), op3);
        System.out.println(resultado);

        sc.close();
    }
}