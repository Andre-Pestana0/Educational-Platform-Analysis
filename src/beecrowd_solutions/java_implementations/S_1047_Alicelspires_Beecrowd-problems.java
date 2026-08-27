import java.util.Scanner;
import java.util.Arrays;

public class Main1047
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        
        int horaI = sc.nextInt();
        int minI = sc.nextInt();
        
        int horaF = sc.nextInt();
        int minF = sc.nextInt();
        
        int horario = 0;
        int minutos = 0;
        
        if(minI <= minF){
             minutos = minF - minI;
        } else {
             minutos = (60 - minI) + minF;
             horaF --;
        }
        
         if(horaI <= horaF){
             horario = horaF - horaI;
        } else {
             horaI = 24 - horaI;
             horario = horaI + horaF;
        }
        
        if(horaI == horaF && minI == minF){
            horario = 24;
            minutos = 0;
        }
        
        System.out.printf("O JOGO DUROU %d HORA(S) E %d MINUTO(S)\n", horario, minutos);

	}
}