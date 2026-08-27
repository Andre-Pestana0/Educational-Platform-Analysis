import java.util.Scanner;

public class Main1020{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int d = sc.nextInt();
        double ano = d/365;
        double mes = (d%365)/30;
        double dia = (d%365)%30;
        
        System.out.printf("%d ano(s)\n", (int)ano);
        System.out.printf("%d mes(es)\n", (int)mes);
        System.out.printf("%d dia(s)\n", (int)dia);
    }
}