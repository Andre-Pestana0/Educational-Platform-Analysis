import java.util.Scanner;

public class Main1131 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int inter, gremio, r;
        int grenais = 0;
        int empate = 0;
        int interP = 0;
        int gremioP = 0;
        
        do{
            inter = sc.nextInt();
            gremio = sc.nextInt();
            grenais++;
            
            if(inter == gremio){
                empate++;
            } else if (inter > gremio){
                interP++;   
            } else {
                gremioP++;
            } 
            
            System.out.println("Novo grenal (1-sim 2-nao)");
            r = sc.nextInt();
            
        } while(r == 1);
        
        System.out.println(grenais + " grenais");
        System.out.println("Inter:"+ interP);
        System.out.println("Gremio:"+ gremioP);
        System.out.println("Empates:"+ empate);
        
        if(inter > gremio){
            System.out.println("Inter venceu mais");
        } else if(gremio > inter){
            System.out.println("Gremio venceu mais");
        } else {
            System.out.println("Nao houve vencedor");
        }
    }
}
