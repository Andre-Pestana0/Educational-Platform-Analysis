import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static class Pair implements Comparable<Pair> {
        long first;
        long second;

        public Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.first != other.first) {
                return Long.compare(this.first, other.first);
            }
            return Long.compare(this.second, other.second);
        }
    }

    static long inv = 0;

    static List<Integer> merge(List<Integer> a, List<Integer> b) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.size() && j < b.size()) {
            if (a.get(i) < b.get(j)) {
                merged.add(a.get(i));
                i++;
            } else {
                inv += a.size() - i;
                merged.add(b.get(j));
                j++;
            }
        }

        while (i < a.size()) {
            merged.add(a.get(i));
            i++;
        }
        while (j < b.size()) {
            merged.add(b.get(j));
            j++;
        }

        return merged;
    }

    static List<Integer> mergeSort(List<Integer> arr) {
        if (arr.size() <= 1) {
            return arr;
        }

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        int mid = arr.size() / 2;

        for (int i = 0; i < mid; ++i) {
            a.add(arr.get(i));
        }

        for (int j = mid; j < arr.size(); ++j) {
            b.add(arr.get(j));
        }

        a = mergeSort(a);
        b = mergeSort(b);

        return merge(a, b);
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();

        if (!scanner.hasNext()) return;

        long x = scanner.nextLong();
        long y = scanner.nextLong();
        long h = scanner.nextLong();
        long v = scanner.nextLong();

        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < h; ++i) {
            long y1 = scanner.nextLong();
            long y2 = scanner.nextLong();
            pairs.add(new Pair(y1, y2));
        }

        Collections.sort(pairs);

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < pairs.size(); ++i) {
            arr.add((int) pairs.get(i).second);
        }

        mergeSort(arr);
        pairs.clear();
        arr.clear();

        for (int i = 0; i < v; ++i) {
            long x1 = scanner.nextLong();
            long x2 = scanner.nextLong();
            pairs.add(new Pair(x1, x2));
        }

        Collections.sort(pairs);

        for (int i = 0; i < pairs.size(); ++i) {
            arr.add((int) pairs.get(i).second);
        }

        mergeSort(arr);

        long resultado = h * v + h + v + inv + 1;
        System.out.println(resultado);
    }

    // Leitor rápido para evitar Time Limit Exceeded (TLE)
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        boolean hasNext() {
            if (st != null && st.hasMoreElements()) return true;
            String line;
            try {
                br.mark(1000);
                line = br.readLine();
                if (line == null) return false;
                br.reset();
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}