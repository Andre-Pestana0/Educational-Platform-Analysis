import java.util.Scanner;

public class Main1099 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        
        for(int i = 0; i < n;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int soma = 0;
            
            for(int j = x+1;j < y; j++){
                if(j%2!=0){
                    soma+=j;
                }
            }
            for(int j = y+1;j < x; j++){
                if(j%2!=0){
                    soma+=j;
                }
            }
            System.out.println(soma);
        }
        
    }
}
