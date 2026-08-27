package mathematics;
import java.util.Scanner;

//https://www.urionlinejudge.com.br/judge/en/problems/view/1193
public class BaseConversion1193 {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int teste = sc.nextInt();
		sc.nextLine();
		for(int i=1;i<=teste;i++){
			String [] valores = sc.nextLine().split(" ");
			System.out.println("Case "+i+":");
			System.out.println(conversor(valores[1],valores[0]));

		}
	}
	
	public static String conversor(String tipo, String valor){
		String resp="";
		if(tipo.equals("bin"))resp = binToDec(valor)+" dec\n"+ binToHex(valor)+" hex\n";
		else if(tipo.equals("dec")) resp = decToHex(valor)+ " hex\n"+ decToBin(valor)+" bin\n";
		else resp = hexToDec(valor)+ " dec\n"+ hexToBin(valor)+" bin\n";
		return resp;
		
	}

	private static int charToDec(char letra){
		switch (letra){
			case 'a':
				return 10;
			case 'b':
				return 11;
			case 'c':
				return 12;
			case 'd':
				return 13;
			case 'e':
				return 14;
			case 'f':
				return 15;
			default:
				return Integer.parseInt(letra+"");
		}
	}
	private static String invert(String valor){
		int i =valor.length()-1;
		String resp ="";
		boolean apaga=true;
		char letra;
		while(i>-1){
			letra = valor.charAt(i);
			apaga = letra == '0' && apaga ? true:false; 
			resp += apaga ? "" : letra+"";
			i--;
		}
		return resp;
	}
	private static String decToBin(String valor) {
		int dec = Integer.parseInt(valor);
		String resp ="";
		while(dec > 3){
			resp += dec%2+"";
			dec /= 2;
		}
		resp += dec%2+""+dec/2;
		return invert(resp);
	}
	
	private static String decToHex(String valor) {	
		 return Integer.toHexString(Integer.parseInt(valor));
	}

	private static int hexToDec(String valor) {
		int resp=0;
		int i = valor.length()-1;
		int j = 0;
		while(i>-1){
			resp+= Math.pow(16, j) * charToDec(valor.charAt(i));
			i--;
			j++;
		}
		return resp;		
	}

	private static String hexToBin(String valor) {
		
		return decToBin(hexToDec(valor)+"");
	}

	private static int binToDec(String valor) {
		int resp=0;
		int i = valor.length()-1;
		int j = 0;
		while(i>-1){
			resp+= Math.pow(2, j)* Integer.parseInt(valor.charAt(i)+"");
			i--;
			j++;
		}
		return resp;
	}
	
	private static String binToHex(String valor) {
		return decToHex(binToDec(valor)+"");
	}
}
