import java.util.Scanner;

public class Main1070{
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    int x = sc.nextInt();
	    int i = 0;
	    while(i < 6){
	        if(x%2 != 0){
	            System.out.println(x);
	            i++;
	        }
	        x++;
	    }
	}
}