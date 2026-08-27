import java.io.*;
import java.util.*;

public class Main {

	static int f, n, m;
	
	public static void pres(int tam, int pos){
		
		if((tam < 1) || (tam > n))
			return;
		if((tam == m) || (n > 50)) {
			f = 1;
			return;
		}
		pres(tam+(pos*2-1),pos+1);
		pres(tam-(pos*2-1),pos+1);
	}
	
	public static void main(String[] args) throws IOException {		

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String linha;
		String[] info;
		
		while (true) {
			
			linha = in.readLine();
			info = linha.split(" ");
			n = Integer.parseInt(info[0]);
			m = Integer.parseInt(info[1]);
			
            if (n == 0 && m == 0) {
                break;
            }				
			
			f = 0;
			pres(1,2);
			
			if(f == 1)
				System.out.println("Let me try!");
			else
				System.out.println("Don't make fun of me!");			
		}		
	}	
}