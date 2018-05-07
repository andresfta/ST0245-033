import java.util.Scanner;
import java.util.ArrayList;
public class Game
{
    static ArrayList<Airport> airports;
    static int actual;
    static int player = 0;

    public static void main(String[] args){
        airports = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int as = sc.nextInt();
        actual = sc.nextInt();
        for(int i = 0; i < as; i++){
            Airport a = new Airport(i + 1);
            airports.add(a);
        }
        // Make the arches
        for(int i = 0; i < as - 1; i++){
            int a1 = sc.nextInt(), a2 = sc.nextInt();
            airports.get(a1 - 1).addSuccessor(airports.get(a2 - 1));
            airports.get(a2 - 1).addSuccessor(airports.get(a1 - 1));
        }
        // Let's start the game
        while(true){
            int min = Integer.MAX_VALUE, next = -1;
            ArrayList<Airport> successors = airports.get(actual - 1).successors;
            for(int i = 0; i < successors.size(); i++){
                Airport visiting = successors.get(i);
                if(visiting.successors != null && visiting.successors.size() < min){
                    min = visiting.successors.size();
                    next = visiting.id;
                }
            }
            if(next == -1)break; else{airports.get(actual - 1).destroy(); actual = next; player = Math.abs(player - 1);}
        }
        String p = "First"; if(player == 0)p = "Second";
        System.out.println(p + " player wins flying to airport " + actual);
    }
    // 4 3 3 2 3 1 1 4
    static class Airport{
        int id;
        ArrayList<Airport> successors;

        Airport(int id){
            this.id = id;
            successors = new ArrayList<>();
        }

        void addSuccessor(Airport a){
            successors.add(a);
        }

        void destroy(){
            successors = null;
        }
    }
}
