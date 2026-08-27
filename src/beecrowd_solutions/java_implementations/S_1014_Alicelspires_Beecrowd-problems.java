import java.util.Scanner;

public class Main1014{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int x = sc.nextInt();
        double y = sc.nextDouble();
        
        double medio = x/y;
        
        System.out.printf("%.3f km/l\n", medio);
    }
}