import java.io.*;

public class Main {
	
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	
	private static int resto(int n, int k) throws IOException {
		int r = 0;
		for (int i = 1; i < n; i++)
			r = (r + k) % i;
        return r;
    }

    public static void main(String[] args) throws IOException {
		int n, y;
		while (true){
			n = Integer.parseInt(in.readLine());
			if (n == 0) break;
			y = 1;
			for (;;){
				if (resto(n,y) != 11) y++;
				else break;
			}
			System.out.println(y);
		}
	}
}