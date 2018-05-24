/**
 * Main algorithms.
 *
 * @author Jamer Rebolledo & Andrés Tamayo
 * @version 2018 I
 */
public class Main
{    
    public static void main(String[] args) throws java.io.FileNotFoundException{
        java.util.Scanner sc = new java.util.Scanner(new java.io.File(args[0]));
        Rectangle r = new Rectangle(-75.55,6.335,0.05,0.035);
        QuadTree q = new QuadTree(r,4);
        sc.nextLine();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            java.util.Scanner inLine = new java.util.Scanner(line);
            inLine.useDelimiter(",");
            double x = Double.parseDouble(inLine.next());
            double y = Double.parseDouble(inLine.next());
            Pee p = new Pee(x, y, 0.0009);
            q.insert(p);
        }
        // q.collisions();
        java.util.ArrayList<Collision> cs = new java.util.ArrayList<>();
        long startTime = System.currentTimeMillis();
        cs = QuadTree.saveCollisions(q, cs);
        long endTime = System.currentTimeMillis();
        System.out.println(cs.size() + "\n" + (endTime - startTime));
        Graphics.main(args);
    }
}
