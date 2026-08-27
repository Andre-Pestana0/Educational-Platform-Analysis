import java.io.*;
import java.util.*;

public class Main {
	
	public static int cont;
	
	public static int fib(int n){		
		cont++;
		if (n < 2) return n;
		return (fib(n-1)+fib(n-2));		
	}
	
	public static void main(String[] args) throws IOException {						
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		
		for (int i = 0; i<n; i++){			
			int x = Integer.parseInt(in.readLine());			
			cont = 0;
			int f = fib(x);
			System.out.format("fib(%d) = %d calls = %d\n", x, cont-1, f);
		}		
	}
}