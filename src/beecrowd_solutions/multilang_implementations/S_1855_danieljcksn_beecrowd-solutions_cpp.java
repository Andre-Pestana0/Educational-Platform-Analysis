import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

public class Main {

    // Classe equivalente ao std::pair<int, int> do C++
    static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();

        Integer columnsToken = scanner.nextInt();
        Integer rowsToken = scanner.nextInt();

        if (columnsToken == null || rowsToken == null) return;

        int columns = columnsToken;
        int rows = rowsToken;

        char[][] map = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            String line = scanner.next();
            for (int j = 0; j < columns; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        Set<Pair> visited = new HashSet<>();
        boolean foundTreasure = false;

        Pair pos = new Pair(0, 0);

        while (true) {
            char currChar = map[pos.first][pos.second];
            visited.add(new Pair(pos.first, pos.second));

            if (currChar == '*') {
                foundTreasure = true;
                break;
            }

            if (currChar == '.') break;

            if (currChar == '<') {
                if (pos.second - 1 < 0) break;

                for (int i = pos.second - 1; i >= 0; --i) {
                    pos.second = i;
                    if (map[pos.first][pos.second] != '.') break;
                }
            } else if (currChar == '>') {
                if (pos.second + 1 >= columns) break;

                for (int i = pos.second + 1; i < columns; ++i) {
                    pos.second = i;
                    if (map[pos.first][pos.second] != '.') break;
                }
            } else if (currChar == '^') {
                if (pos.first - 1 < 0) break;

                for (int i = pos.first - 1; i >= 0; --i) {
                    pos.first = i;
                    if (map[pos.first][pos.second] != '.') break;
                }
            } else { // 'v'
                if (pos.first + 1 >= rows) break;

                for (int i = pos.first + 1; i < rows; ++i) {
                    pos.first = i;
                    if (map[pos.first][pos.second] != '.') break;
                }
            }

            if (visited.contains(pos)) {
                break;
            }
        }

        System.out.println(foundTreasure ? '*' : '!');
    }

    // Class para I/O Rápido
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        Integer nextInt() {
            String s = next();
            if (s == null) return null;
            return Integer.parseInt(s);
        }
    }
}