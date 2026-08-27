import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class URI2136 {

    public static class Participant{
        String name;
        boolean didSayYes;
        
        public Participant(String name, boolean didSayYes){
            this.name = name;
            this.didSayYes = didSayYes;
        }

        public boolean comparaWinner(Participant p) {
            return p.didSayYes && (p.name.length() > this.name.length());
        }

        public int comparaTo(Participant p) {
            if (!Objects.equals(this.didSayYes, p.didSayYes)){
                return this.didSayYes ? -1 : 1;
            }
            else{
                return this.name.compareTo(p.name);
            }
        }
    }
 
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        List<Participant> list = new ArrayList<Participant>();
        Participant winner = new Participant("", false);

        while (entrada.hasNext()){
            String input = entrada.nextLine();
            String[] parts = input.split(" ");

            if ((parts.length > 1)){
                boolean didSayYes = Objects.equals(parts[1], new String("YES"));
                String name = parts[0];
                Participant participant = new Participant(name, didSayYes);
                if (!list.stream().anyMatch(p -> name.equals(p.name))){
                    list.add(participant);
                }
            }
            else{
                break;
            }
        }
        entrada.close();

        for(int i = 0; i < list.size(); i++){
            Participant current = list.get(i);
            if (winner.comparaWinner(current)){
                winner = current;
            }
        }

        Participant temp;
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.size()-1; i++) {
                if (list.get(i).comparaTo(list.get(i + 1)) > 0) {
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    sorted = false;
                }
            }
        }

        for (Participant participant: list){
            System.out.println(participant.name);
         }
         System.out.println();
         System.out.println("Amigo do Habay:");
         System.out.println(winner.name);

    }

}