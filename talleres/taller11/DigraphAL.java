import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
/**
 * Esta clase es una implementación de un digrafo usando listas de adyacencia
 * 
 * @author Mauricio Toro 
 * @version 1
 */
public class DigraphAL extends Graph
{
    ArrayList<LinkedList<Integer>> adyacencia;
    public DigraphAL(int size)
    {
        super(size);
        adyacencia = new ArrayList<LinkedList<Integer>>();
        for(int i = 0; i < size; i++){
            LinkedList<Integer> l = new LinkedList<>();
            for(int j = 0; j < size; j++)l.add(0);
            adyacencia.add(l);
        }
    }

    public void addArc(int source, int destination, int weight)
    {
        adyacencia.get(source).add(destination, weight);
        adyacencia.get(source).remove(destination + 1);
    }

    public int getWeight(int source, int destination)
    {
        return adyacencia.get(source).get(destination);
    }

    public ArrayList<Integer> getSuccessors(int vertice)
    {
        ArrayList<Integer> a = new ArrayList<>();
        for(int i = 0; i < adyacencia.get(vertice).size(); i++)if(adyacencia.get(vertice).get(i) != 0)a.add(i);
        return a;
    }
}
