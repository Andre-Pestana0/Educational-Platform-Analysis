import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String linha;
		int pos, tamanho, i, tam;
		StringBuilder linha_aux;
		
		while ((linha = in.readLine()) != null)
		{			
			tamanho = linha.length();
			linha_aux = new StringBuilder();
			i = 0;
			for (i = 0,pos=0,tam=0; i<tamanho; i++){				
				if (linha.charAt(i) == ']'){
					pos = tam;
				}
				else if (linha.charAt(i) == '['){
					pos = 0;
				}								
				if ((linha.charAt(i) != '[') && (linha.charAt(i) !=']')) {
					linha_aux.insert(pos++, linha.charAt(i));
					tam++;
				}
			}
			System.out.println(linha_aux);			
		}
	}
}
