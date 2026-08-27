import java.io.*;
import java.util.*;

public class Main {		
	
	public static void main(String[] args) throws IOException {
						
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
				
		String n;
		
		int[] f = new int[1600];
		f[1]=f[2]=1;
		for (int i=3; i<=1510;i++){			
			f[i] = (f[i-1]%1000 + f[i-2]%1000)%1000;			
		}
			
		int T = Integer.parseInt(in.readLine());
		
		for (int i = 0; i<T; i++){
		
			n = in.readLine();			
			
			int v = 0, b = 1;			
			for (int j=n.length()-1;j>=0;j--){
				if (n.charAt(j) == '1') v = (v + b)%1500;
				b *=2;	
				b %=1500;
			}			
			v %= 1500;			
			
			System.out.format("%03d\n",f[v]);
		}		
	}
}