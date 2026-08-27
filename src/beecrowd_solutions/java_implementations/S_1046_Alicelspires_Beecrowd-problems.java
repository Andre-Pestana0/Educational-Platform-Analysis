import java.util.Scanner;
import java.util.Arrays;

public class Main1046
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        
        int horaI = sc.nextInt();
        int horaF = sc.nextInt();
        
        int horario = 0;
        
        if(horaI < horaF){
            horario = horaF - horaI;
        } else {
            horaI = 24 - horaI;
            horario = horaI + horaF;
        }
        
        System.out.printf("O JOGO DUROU %d HORA(S)\n", horario);

	}
} 