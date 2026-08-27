import java.util.Scanner;

public class Main1097 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i = 1; i <= 9; i+=2){
	        int j = 6+i;
            for(int n = 0; n < 3; n++){
                System.out.print("I="+i+" ");
                System.out.println("J="+j);
                j--;
            }
	    }
        
    }
}
