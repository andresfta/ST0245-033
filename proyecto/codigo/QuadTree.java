/**
 * Clase QuadTree.
 *
 * Jamer Rebolledo & Andrés Tamayo.
 * 2018 I
 */
public class QuadTree
{
    Rectangle boundary;
    int capacity;
    java.util.ArrayList<Pee> pees = new java.util.ArrayList<>();
    boolean divided = false;
    QuadTree northwest;
    QuadTree northeast;
    QuadTree southwest;
    QuadTree southeast;
    java.util.ArrayList<Collision> collisions = new java.util.ArrayList<>();

    /**
     * QuadTree Constructor
     *
     * @param boundary Los límites del QuadTree
     * @param capacity Máxima capacidad de abejas para el QuadTree
     */
    QuadTree(Rectangle boundary, int capacity) throws java.io.FileNotFoundException{
        this.boundary = boundary;
        this.capacity = capacity;
    }

    /**
     * Método insert, inserta una abeja al QuadTree   
     *
     * @param p La abeja a insertar
     */
    boolean insert(Pee p) throws java.io.FileNotFoundException{
        if(!boundary.contains(p)){
            return false;
        }
        if(pees.size() < capacity){
            pees.add(p); return true;
        }else{
            if(!divided){
                split();
            }
        }
        if(northwest.insert(p))return true;
        if(northeast.insert(p))return true;
        if(southwest.insert(p))return true;
        if(southeast.insert(p))return true;
        return false;
    }

    /**
     * Método split, cuando el QuadTree alcanza su máxima capacidad, lo divide en más cuadrantes.
     *
     */
    void split() throws java.io.FileNotFoundException{
        double x = boundary.x;
        double y = boundary.y;
        double w = boundary.w;
        double h = boundary.h;
        Rectangle ne = new Rectangle(x + w / 2, y - h / 2, w / 2, h / 2);
        Rectangle nw = new Rectangle(x - w / 2, y - h / 2, w / 2, h / 2);
        Rectangle se = new Rectangle(x + w / 2, y + h / 2, w / 2, h / 2);
        Rectangle sw = new Rectangle(x - w / 2, y + h / 2, w / 2, h / 2);
        northwest = new QuadTree(nw, capacity);
        northeast = new QuadTree(ne, capacity);
        southwest = new QuadTree(sw, capacity);
        southeast = new QuadTree(se, capacity);
        divided = true;
    }

    /**
     * Method collisions, para detectar colisiones entre abejas que están a menos de 100 metros de distancia
     *
     */
    void collisions() throws java.io.FileNotFoundException{
        for(int i = 0; i < pees.size(); i++){
            Pee temp = pees.get(i);
            for(int j = i + 1; j < pees.size(); j++){
                if(temp.intersects(pees.get(j))){
                    Collision c = new Collision(pees.get(i), pees.get(j));
                    // collisions.add(c);
                    System.out.println(c);
                }
            }
        }
        if(divided){
            northwest.collisions();
            northeast.collisions();
            southwest.collisions();
            southeast.collisions();
        }
    }
    
    static java.util.ArrayList<Collision> saveCollisions(QuadTree q, java.util.ArrayList<Collision> cs){
        if(!q.divided)return cs;
        for(int i = 0; i < q.pees.size(); i++){
            Pee temp = q.pees.get(i);
            for(int j = i + 1; j < q.pees.size(); j++){
                if(temp.intersects(q.pees.get(j))){
                    Collision c = new Collision(q.pees.get(i), q.pees.get(j));
                    // collisions.add(c);
                    // System.out.println(c);
                    cs.add(c);
                }
            }
        }
        saveCollisions(q.northwest,cs);
        saveCollisions(q.northeast,cs);
        saveCollisions(q.southwest,cs);
        saveCollisions(q.southeast,cs);
        return cs;
    }
}
