import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {		

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int c = Integer.parseInt(in.readLine());		

		for (int i = 0; i<c; i++){
			
			String linha = in.readLine();
			
			String[] info = linha.split(" ");
		  
			int n = Integer.parseInt(info[0]);
			int m = Integer.parseInt(info[1]);
				 
			if (n == 0 && m == 0) {
				System.out.println("0");
			}
			else{
				long resp = 1;
				
				while (n>0) {
					resp *= 26;
					--n;
				}
			
				while (m>0){
					resp *= 10;
					--m;
				}
				System.out.println(resp);
			}
		}
	}
}