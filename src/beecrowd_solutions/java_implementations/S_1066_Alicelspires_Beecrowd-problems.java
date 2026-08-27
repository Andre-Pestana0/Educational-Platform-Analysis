import java.util.Scanner;

public class Main1066 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int pares = 0;
		int impar = 0;
		int negativo = 0;
		int positivo = 0;

		for(int i = 0; i < 5; i++) {
			int valor = sc.nextInt();

			if(valor%2 == 0) {
				pares++;
			} else {
				impar++;
			}
			if(valor > 0) {
				positivo++;
			} else if(valor < 0) {
				negativo++;
			}
		}

		System.out.println(pares +" valor(es) par(es)");
        System.out.println(impar +" valor(es) impar(es)");
        System.out.println(positivo +" valor(es) positivo(s)");
        System.out.println(negativo +" valor(es) negativo(s)");
	}
}