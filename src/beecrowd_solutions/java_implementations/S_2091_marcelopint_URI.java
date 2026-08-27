import java.io.*;
import java.util.*;

public class Main {	

    public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		String linha;
		long n, res = 0;
		

		while (true){
			n = Long.parseLong(in.nextLine());
			
			if (n == 0) break;			

			linha = in.nextLine();
			
			
			String[] num = linha.split(" ");
						
			if (!linha.equals("")){
				res = 0;
				for (int i = 0; i < n; i++){
					res = res ^ Long.parseLong(num[i]);
				}
			}
			System.out.println(res);
		}
	}
}