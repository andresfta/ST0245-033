import java.util.ArrayList;
/**
 * Esta clase es una implementación de un digrafo usando listas de adyacencia
 * 
 * @author Mauricio Toro 
 * @version 1
 */
public class DigraphAL extends Graph
{
    ArrayList<ArrayList<Pareja>> l;
    
    public DigraphAL(int size)
    {
        super(size);
        l = new ArrayList<>();
        for(int i = 0; i < size; i++){
            ArrayList<Pareja> ps = new ArrayList<>();
            l.add(ps);
        }
    }

    public void addArc(int source, int destination, int weight)
    {
        Pareja p = new Pareja(destination, weight);
        l.get(source).add(p);
    }

    public int getWeight(int source, int destination)
    {
        int peso = 0;
        for(Pareja p: l.get(source))if(p.vertice == destination)peso = p.peso;
        return peso;
    }

    public ArrayList<Integer> getSuccessors(int vertice)
    {
        ArrayList<Integer> successors = new ArrayList<>();
        for(Pareja p: l.get(vertice))successors.add(p.vertice);
        return successors;
    }
}
