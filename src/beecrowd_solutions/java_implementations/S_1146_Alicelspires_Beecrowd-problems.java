import java.util.Scanner;

public class Main1146 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while(true){
            int n = sc.nextInt();
            String r = "";
            if(n==0) break;
            
            r+="1";
            for(int i = 2; i <= n;i++){
                r+=" ";
                r+= Integer.toString(i);
            }
            System.out.println(r);
        }
        
    }
}
