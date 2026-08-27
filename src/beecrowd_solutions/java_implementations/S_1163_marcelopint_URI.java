import java.io.*;
import java.util.*;

public class Main {
	
	public static final double pi = 3.14159;
	public static final double g = 9.80665;
	
	public static void main(String[] args) throws IOException {
						
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
		
		String linha;
		
		while ((linha = in.readLine()) != null) {
			
			double h = Double.parseDouble(linha);
			
			linha = in.readLine();
			String[] info = linha.split(" ");
			
			int p1 = Integer.parseInt(info[0]);
			int p2 = Integer.parseInt(info[1]);
			
			int n = Integer.parseInt(in.readLine());
			
			while (n-- > 0) {			
				linha = in.readLine();
				info = linha.split(" ");
			
				double ang = Double.parseDouble(info[0]);
				double v = Double.parseDouble(info[1]);
				
				ang = ang * pi / 180.0;
				double sx = v * Math.cos(ang);
				double sy = v * Math.sin(ang);
				double z = g * h / (v * v);
				double t = (v / g) * (Math.sin(ang) + Math.sqrt(Math.sin(ang) * Math.sin(ang) + 2 * z));
				double x = sx * t;
				System.out.format("%.5f -> ",x);
				System.out.println((x >= p1 && x <= p2) ? "DUCK" : "NUCK");
			}
		}
	}
}