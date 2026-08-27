import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);

        // Dados de Gunnar
        int a1 = Integer.parseInt(st.nextToken());
        int b1 = Integer.parseInt(st.nextToken());
        int a2 = Integer.parseInt(st.nextToken());
        int b2 = Integer.parseInt(st.nextToken());

        int sizeGunnar1 = b1 - a1 + 1;
        int[] gunnar1 = new int[sizeGunnar1];
        for (int i = 0; i < sizeGunnar1; ++i) {
            gunnar1[i] = a1 + i;
        }

        int sizeGunnar2 = b2 - a2 + 1;
        int[] gunnar2 = new int[sizeGunnar2];
        for (int i = 0; i < sizeGunnar2; ++i) {
            gunnar2[i] = a2 + i;
        }

        // Dados de Emma
        st = ensureTokens(st, reader);
        a1 = Integer.parseInt(st.nextToken());
        b1 = Integer.parseInt(st.nextToken());
        a2 = Integer.parseInt(st.nextToken());
        b2 = Integer.parseInt(st.nextToken());

        int sizeEmma1 = b1 - a1 + 1;
        int[] emma1 = new int[sizeEmma1];
        for (int i = 0; i < sizeEmma1; ++i) {
            emma1[i] = a1 + i;
        }

        int sizeEmma2 = b2 - a2 + 1;
        int[] emma2 = new int[sizeEmma2];
        for (int i = 0; i < sizeEmma2; ++i) {
            emma2[i] = a2 + i;
        }

        int size1 = sizeGunnar1 * sizeGunnar2;
        int size2 = sizeEmma1 * sizeEmma2;

        int[] gunnarComb = new int[size1];
        int[] emmaComb = new int[size2];

        int idxComb = 0;
        for (int i = 0; i < sizeGunnar1; ++i) {
            for (int j = 0; j < sizeGunnar2; ++j) {
                gunnarComb[idxComb] = gunnar1[i] + gunnar2[j];
                idxComb++;
            }
        }

        idxComb = 0;
        for (int i = 0; i < sizeEmma1; ++i) {
            for (int j = 0; j < sizeEmma2; ++j) {
                emmaComb[idxComb] = emma1[i] + emma2[j];
                idxComb++;
            }
        }

        int probGunnar = 0;
        int probEmma = 0;

        for (int i = 0; i < size1; ++i) {
            for (int j = 0; j < size2; ++j) {
                if (gunnarComb[i] > emmaComb[j]) {
                    probGunnar++;
                } else if (gunnarComb[i] < emmaComb[j]) {
                    probEmma++;
                }
            }
        }

        if (probGunnar > probEmma) {
            System.out.println("Gunnar");
        } else if (probEmma > probGunnar) {
            System.out.println("Emma");
        } else {
            System.out.println("Tie");
        }
    }

    private static StringTokenizer ensureTokens(StringTokenizer st, BufferedReader reader) throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) break;
            st = new StringTokenizer(line);
        }
        return st;
    }
}