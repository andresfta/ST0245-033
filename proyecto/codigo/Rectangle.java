/**
 * Clase Rectangulo, para representar el límite sobre el cual se está trabajando.
 *
 * @author Jamer Rebolledo & Andrés Tamayo
 * @version 2018 I
 */
public class Rectangle
{
    double x, y, w, h;
    
    /**
     * Rectangle Constructor
     *
     * @param x Coordenada central en x
     * @param y Coordenada central en y
     * @param w El ancho del rectangulo
     * @param h La altura del rectangulo
     */
    Rectangle(double x, double y, double w, double h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    /**
     * Method contains, verifica si una abeja está dentro del rectangulo
     *
     * @param p La abeja a verificar
     * @return true, si está adentro, false en otro caso.
     */
    boolean contains(Pee p){
        return p.x >= x - w && p.x <= x + w && p.y >= y - h && p.y <= y + h;
    }
}
