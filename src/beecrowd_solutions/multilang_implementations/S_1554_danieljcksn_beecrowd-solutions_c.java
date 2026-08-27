import java.io.InputStream;
import java.io.IOException;

public class Main {

    // FastScanner simples que lê token por token evitando erros de quebra de linha do StringTokenizer
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;

        private boolean hasNextByte() {
            if (ptr < buflen) return true;
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buflen > 0;
        }

        private int readByte() {
            if (hasNextByte()) return buffer[ptr++];
            return -1;
        }

        public boolean hasNext() {
            while (hasNextByte() && buffer[ptr] <= ' ') ptr++;
            return hasNextByte();
        }

        public int nextInt() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            int c = readByte();
            while (c <= ' ') c = readByte();
            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = readByte();
            }
            int res = 0;
            while (c >= '0' && c <= '9') {
                res = res * 10 + (c - '0');
                c = readByte();
            }
            return negative ? -res : res;
        }
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();

        if (!sc.hasNext()) return;

        int c = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (c-- > 0) {
            int n = sc.nextInt();

            // Coordenadas da bola branca
            int x = sc.nextInt();
            int y = sc.nextInt();

            int menorD2 = Integer.MAX_VALUE;
            int bola = 1;

            // Lê as coordenadas das N bolas
            for (int i = 0; i < n; i++) {
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();

                int dx = x1 - x;
                int dy = y1 - y;

                // Distância ao quadrado
                int d2 = dx * dx + dy * dy;

                if (d2 < menorD2) {
                    menorD2 = d2;
                    bola = i + 1;
                }
            }

            sb.append(bola).append("\n");
        }

        System.out.print(sb.toString());
    }
}