import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static class Ride {
        int duration;
        int score;

        Ride(int duration, int score) {
            this.duration = duration;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        int instance = 1;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            int n = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (n == 0) break;

            Ride[] rides = new Ride[n];

            for (int i = 0; i < n; i++) {
                while (st == null || !st.hasMoreTokens()) {
                    String nextLine = br.readLine();
                    if (nextLine == null) break;
                    st = new StringTokenizer(nextLine);
                }
                int duration = Integer.parseInt(st.nextToken());
                int score = Integer.parseInt(st.nextToken());
                rides[i] = new Ride(duration, score);
            }

            // Tabela de Programação Dinâmica
            int[] points = new int[t + 1];

            for (int time = 1; time <= t; time++) {
                for (Ride ride : rides) {
                    if (time >= ride.duration) {
                        points[time] = Math.max(points[time], ride.score + points[time - ride.duration]);
                    }
                }
            }

            // Formatação de saída conforme o original
            sb.append("Instancia ").append(instance).append("\n");
            sb.append(points[t]).append("\n\n");
            instance++;
        }

        System.out.print(sb.toString());
    }
}