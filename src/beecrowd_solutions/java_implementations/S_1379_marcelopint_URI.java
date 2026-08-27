import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
						
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String linha;
		String[] info;		
		int[] num = {0,0};
				
		while (true) {
			linha = in.readLine();
			info = linha.split(" ");
			num[0] = Integer.parseInt(info[0]);
			num[1] = Integer.parseInt(info[1]);
			
            if (num[0] == 0 && num[1] == 0) {
                break;
            }
            Arrays.sort(num);
            System.out.println(2 * num[0] - num[1]);		
		}
	}
}