import java.util.Scanner;

public class Main1149{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int A = sc.nextInt();
	    int N = sc.nextInt();
	    
	    while(N <= 0){
	        N = sc.nextInt();
	    }
	    
	    int soma = 0;
        for(int i = 0; i < N; i++){
            soma += A+i;
        }
        System.out.println(soma);
	}
}
