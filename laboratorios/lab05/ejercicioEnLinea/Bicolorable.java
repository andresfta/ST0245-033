import java.util.Scanner;
import java.util.ArrayList;
/**
 * Part 2
 *
 * @author Jamer Rebolledo & Andrés Tamayo
 * @version May 2018
 */
public class Bicorolable
{
    /**
     * Method bicolorable
     * Check if a directed graph is bicolorable, that is to say, if we can paint all nodes using only two colors in such a way that
     * two adjacent nodes don't have the same color.
     */
    public static void bicolorable(){
        Scanner sc = new Scanner(System.in); // C1
        while(true){ // n
            int vertices = sc.nextInt(); // C2
            if(vertices == 0)break; // C3
            int arches = sc.nextInt(); // C4
            int[][] matriz = new int[vertices][vertices]; // C5
            for(int i = 0; i < arches; i++){ // m
                int vertex = sc.nextInt(), destination = sc.nextInt(); // C6
                matriz[vertex][destination] = 1; // C7
                matriz[destination][vertex] = 1; // C8
            }
            Boolean[] colors = new Boolean[vertices]; // C9
            colors[0] = true; // C10
            for(int i = 0; i < matriz[0].length; i++){if(matriz[0][i] == 1)colors[i] = false;} // n
            for(int i = 1; i < vertices; i++){ // v *
                if(colors[i] == null)colors[i] = true; boolean b = !colors[i]; // C11
                for(int j = 0; j < vertices; j++){ // v
                    if(matriz[i][j] == 1)colors[j] = b; // C12
                }
            }
            String s = "BICOLORABLE."; // C13
            for(int i = 0; i < matriz.length; i++){ // v *
                for(int j = 0; j < matriz[i].length; j++){ // v
                    if(matriz[i][j] == 1)if(colors[i] == colors[j]){s = "NOT BICOLORABLE."; break;} // C14
                }
            }
            System.out.println(s); // C15
        }
        // Complejidad O(n * m * v^2);
        // 3 3 0 1 1 2 2 0 3 2 0 1 1 2 9 8 0 1 0 2 0 3 0 4 0 5 0 6 0 7 0 8 0
    }
}
