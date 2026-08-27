import java.util.Scanner;

public class Main1181{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int linha = sc.nextInt();
        char oper = sc.next().charAt(0);
        
        double[][] m = new double[12][12];
        double soma = 0;
        double media = 0;
        
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){
                m[i][j] = sc.nextDouble();
            }
        }
        
        if(oper == 'S'){
            for(int k = 0; k < 12; k++){
                soma += m[linha][k];
            }
        } else if(oper == 'M'){
            for(int k = 0; k < 12; k++){
                media += m[linha][k];
            }
        }
        media /= m[0].length;
        System.out.printf("%.1f\n", oper == 'S' ? soma : media);
    }
}