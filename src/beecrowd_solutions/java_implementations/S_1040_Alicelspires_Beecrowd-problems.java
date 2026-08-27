import java.util.Scanner;

public class Main1040
{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	   
	    double n1 = sc.nextDouble();  
        double n2 = sc.nextDouble(); 
        double n3 = sc.nextDouble(); 
        double n4 = sc.nextDouble(); 
        
        double media = (n1*2+n2*3+n3*4+n4*1)/10;
        media = Math.floor(media*10.0)/10.0;
        
        System.out.printf("Media: %.1f\n", media);
        
        if(media >=7.0){
            
            System.out.println("Aluno aprovado.");
            
        } else if(media < 5.0){
            
            System.out.println("Aluno reprovado.");
            
        } else if(5.0 <= media && media <= 6.9){
            
            System.out.println("Aluno em exame.");
            
            double notaExame = sc.nextDouble();
            System.out.printf("Nota do exame: %.1f\n", notaExame);
            double novaMedia = (media+notaExame)/2;
            
            if(novaMedia >= 5.0){
                System.out.println("Aluno aprovado.");
            } else {
                System.out.println("Aluno reprovado.");
            }
            
            System.out.printf("Media final: %.1f\n", novaMedia);
            
        }
	}
}