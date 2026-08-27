import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static void printQueue(Queue<String> q) {
        Queue<String> temp = new LinkedList<>(q);
        while (!temp.isEmpty()) {
            System.out.print(temp.poll() + (temp.size() > 0 ? ", " : ""));
        }
        System.out.println();
    }

    static boolean match(String s, String pattern) {
        int n = pattern.length();
        int i, j;

        Queue<String> q = new LinkedList<>();
        Set<String> inserted = new HashSet<>();

        q.add(s);
        inserted.add(s);

        for (i = 0; i < n; ++i) {
            if (pattern.charAt(i) == '*' && i == n - 1 && !q.isEmpty()) {
                return true;
            }

            char currentChar;

            if (pattern.charAt(i) != '*') {
                currentChar = pattern.charAt(i);

                Queue<String> newq = new LinkedList<>();
                boolean anyMatch = false;

                inserted.clear();

                while (!q.isEmpty()) {
                    String str = q.poll();

                    if (str.length() > 0 && str.charAt(0) == currentChar) {
                        if (str.length() > 1) {
                            String sbb = str.substring(1);

                            if (!inserted.contains(sbb)) {
                                inserted.add(sbb);
                                newq.add(sbb);
                            }
                        } else if (str.length() == 1) {
                            anyMatch = true;
                        }
                    }
                }

                if (i == n - 1 && anyMatch) {
                    return true;
                }

                q = newq;
                continue;
            }

            for (j = i + 1; j < n; ++j) {
                if (pattern.charAt(j) != '*') {
                    currentChar = pattern.charAt(j);
                    i = j - 1;
                    break;
                }
            }

            currentChar = pattern.charAt(i + 1);
            Queue<String> newq = new LinkedList<>();

            inserted.clear();

            while (!q.isEmpty()) {
                String str = q.poll();

                for (int subs = 0; subs < str.length(); ++subs) {
                    if (str.charAt(subs) == currentChar) {
                        if (str.length() > 1) {
                            String sbb = str.substring(subs);
                            if (!inserted.contains(sbb)) {
                                inserted.add(sbb);
                                newq.add(sbb);
                            }
                        } else {
                            newq.add(str);
                        }
                    }
                }
            }

            q = newq;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (line != null && line.trim().isEmpty()) {
            line = reader.readLine();
        }
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        String p = st.nextToken();

        st = ensureTokens(st, reader);
        int n = Integer.parseInt(st.nextToken());

        String[] files = new String[n];
        for (int i = 0; i < n; ++i) {
            st = ensureTokens(st, reader);
            files[i] = st.nextToken();
        }

        StringBuilder sb = new StringBuilder();
        for (String file : files) {
            if (match(file, p)) {
                sb.append(file).append('\n');
            }
        }

        System.out.print(sb.toString());
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