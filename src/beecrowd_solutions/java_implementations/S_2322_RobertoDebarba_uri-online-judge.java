import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int qtdPecas = Integer.parseInt(br.readLine());
		String[] pecas = br.readLine().split(" ");
		int[] arrayPrincipal = new int[qtdPecas];
		for (int i = 0; i < pecas.length; i++) {
			arrayPrincipal[Integer.parseInt(pecas[i])-1] = 1;
		}
		for (int i = 0; i < qtdPecas; i++) {
			if (arrayPrincipal[i] == 0) {

				System.out.println(i + 1);
				break;
			}
		}

	}

}
