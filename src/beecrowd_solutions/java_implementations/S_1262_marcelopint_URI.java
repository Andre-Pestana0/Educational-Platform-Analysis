import java.io.*;

public class Main {	
	
    public static void main(String[] args) throws IOException {		

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int p, tam, ciclos, cont;
		String linha;		
		
		while ((linha = in.readLine()) != null){ 
			
			p = Integer.parseInt(in.readLine());
			tam = linha.length();
			ciclos = 0;
			cont = 0;
			for (int i=0;i<tam;i++){
				if(linha.charAt(i) == 'R'){
					if(cont == 0) 
						ciclos++;
					cont++;
					if(cont == p)
						cont = 0;
				}else{
					ciclos++;
					cont = 0;
				}
			}
			System.out.println(ciclos);
		}
	}
}
