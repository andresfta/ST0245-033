import java.util.ArrayList;
/**
 * Esta clase es una implementación de un digrafo usando matrices de adyacencia
 * 
 * @author Mauricio Toro 
 * @version 1
 */
public class DigraphAM extends Graph
{
    int[][] matriz;
    
    public DigraphAM(int size)
    {
        super(size);
        matriz = new int[size][size];
    }

    public int getWeight(int source, int destination)
    {
        return matriz[source][destination];
    }

    public void addArc(int source, int destination, int weight)
    {
        matriz[source][destination] = weight;
    }
    
    public void removeNode(int source){
        for(int i = 0; i < matriz[source].length; i++){matriz[source][i] = 0;}
    }
    
    int lessSuccessors(){
        int min = Integer.MAX_VALUE, vertex = -1;
        // for(int i = 0; i < matriz.
        return -1;
    }

    public ArrayList<Integer> getSuccessors(int vertex)
    {
        ArrayList<Integer> a = new ArrayList<>();
        for(int i = 0; i < matriz[vertex].length; i++)if(matriz[vertex][i] != 0)a.add(i);
        return a;
    }
}
