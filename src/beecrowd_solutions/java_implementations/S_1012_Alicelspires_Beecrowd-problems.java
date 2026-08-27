import java.util.Scanner;

public class Main1012{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        double A = sc.nextDouble();
        double B = sc.nextDouble();
        double C = sc.nextDouble();
        
        double areaTri = A*C/2;
        double areaCirc = 3.14159*Math.pow(C, 2);
        double areaTrape = (A+B)*C/2;
        double areaQuadr = B*B;
        double areaRetan = A*B;
        
        System.out.printf("TRIANGULO: %.3f\n", areaTri);
        System.out.printf("CIRCULO: %.3f\n", areaCirc);
        System.out.printf("TRAPEZIO: %.3f\n", areaTrape);
        System.out.printf("QUADRADO: %.3f\n", areaQuadr);
        System.out.printf("RETANGULO: %.3f\n", areaRetan);
    }
}