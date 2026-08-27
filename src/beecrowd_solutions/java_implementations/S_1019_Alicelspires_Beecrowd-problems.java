import java.util.Scanner;

public class Main1019{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        double v = sc.nextInt();
        double h = v/3600;
        double m = (v%3600)/60;
        double s = (v%3600)%60;
        
        System.out.printf("%d:%d:%d\n", (int)h, (int)m, (int)s);
    }
}