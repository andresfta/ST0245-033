/**
 * Clase Pee, para representar una abeja.
 *
 * @author Jamer Rebolledo & Andrés Tamayo.
 * @version 2018 I
 */
public class Pee
{
    double x, y, r;
    
    Pee(double x, double y, double r){
        this.x = x;
        this.y = y;
        this.r = r;
    }
    
    boolean intersects(Pee p){
        double distance = distance(x, y, p.x, p.y);
        return distance < r + p.r;      
    }
    
    double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}


/**
 * Clase Collision, registra una colisión
 *
 * @author Jamer Rebolledo & Andrés Tamayo
 * @version 2018 I
 */
class Collision
{
    Pee p1;
    Pee p2;
    
    Collision(Pee p1, Pee p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public String toString(){
        // return String.format("P1 x: %.11f, y: %.11f \t P2 x: %.11f, y: %.11f", p1.x, p1.y, p2.x, p2.y);
        return String.format("%.11f,%.11f\n%.11f,%.11f", p1.x, p1.y,p2.x,p2.y);
    }
}

