import java.io.*;
import java.util.*;

public class Main {

	public static StringBuilder saida;
	
	public static void pos(String pre, String in){
		if ((pre.length() == 0) || (in.length() == 0)) return;
		
		int n = pre.length(), i = in.indexOf(pre.charAt(0));
		
		pos(pre.substring(1,i+1), in.substring(0, i));
		pos(pre.substring(i+1, n), in.substring(i+1,n));
		saida.append(pre.charAt(0));
	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		

		int c = Integer.parseInt(in.readLine());		

		for (int i = 0; i<c; i++){
			
			String linha = in.readLine();
			
			String[] s = linha.split("\\s");
			
			saida = new StringBuilder();
			
			int n = Integer.parseInt(s[0]);
			
			pos(s[1], s[2]);			
			
			System.out.println(saida);
			
		}
	}
}