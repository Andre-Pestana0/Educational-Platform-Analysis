import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String linha, ca = "";
		String[] info;
		int n, m, i;
		
		while (true) {
			
			linha = in.readLine();
			info = linha.split(" ");
			n = Integer.parseInt(info[0]);
			m = Integer.parseInt(info[1]);
			
            if (n == 0 && m == 0) {
                break;
            }
			
			String[] emo = new String[n];
			
			for (i=0;i<n;i++){
				emo[i] = in.readLine();
				ca += emo[i].charAt(emo[i].length()-1);
			}
			
			Arrays.sort(emo);
			
			StringBuilder[] frase = new StringBuilder[m];
			
			int cont = 0;
			//frases
			for (i=0;i<m;i++){
				
				frase[i] = new StringBuilder();
				frase[i].append(in.readLine());
				
				for (int k=0;k<frase[i].length();k++){
					
					if (ca.contains(frase[i].substring(k,k+1))) {
						for (int j=0;j<n;j++){
							if ((k+1) >= emo[j].length()){							
								if ((frase[i].charAt(k) == emo[j].charAt(emo[j].length()-1)) && (frase[i].substring(k+1-emo[j].length(),k+1).equals(emo[j]))){
									frase[i].setCharAt(k,'@');
									cont++;
								}
							}
						}
					}
				}
			}			
			System.out.println(cont);			
		}
	}
}