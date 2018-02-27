import java.util.Scanner;
import java.util.ArrayList;
public class Point2_and_3
{
    static void point2(String[] args){
        Scanner s = new Scanner(System.in);
        ArrayList<Integer> a = new ArrayList<>();
        int n = s.nextInt();
        a.add(n);
        while(n != -1){
            n = s.nextInt();
            a.add(0, n);
        }
        System.out.println(a);
    }
    
    static void point3(int n){
        ArrayList<Integer> a = new ArrayList<>();
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < i + 1; j++){
                a.add(j);
            }
        }
        System.out.println(a);
    }
}
