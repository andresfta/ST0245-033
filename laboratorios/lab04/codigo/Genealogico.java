public class Genealogico
{
    Persona ancestro;
    private int generaciones = 0;
    
    Genealogico(){
        ancestro = null;
    }
    
    void insertarAncestro(String nombre, boolean sex){
        ancestro = new Persona(nombre, sex);
        generaciones = 1;
    }
    
    private void insertar(Persona p, String nombre, String nombrePadre, boolean sexo){
        if(p == null){return;}
        if(p.nombre.equals(nombrePadre)){
            if(sexo)p.hijo = new Persona(nombre, sexo);
            else p.hija = new Persona(nombre, sexo);
        }else{
            insertar(p.hijo, nombre, nombrePadre, sexo);
            insertar(p.hija, nombre, nombrePadre, sexo);
        }
    }
    
    /**
     * Method insertar
     * Insert a person that have a father in the tree.
     * @param nombre The name of the person that you want insert.
     * @param nombrePadre The name of his father.
     * @param sex The sex of the person, true if is a man, false if is a woman.
     */
    void insertar(String nombre, String nombrePadre, boolean sex){
        insertar(ancestro, nombre, nombrePadre, sex);
    }
    
    private int generaciones(Persona p, int g){
        if(p == null)return g;
        return generaciones(p.hijo, g + 1);
    }
    
    int altura(){
        generaciones = generaciones(ancestro, 1);
        return generaciones;
    }
    
    public static void main(String[] args){
        Genealogico g = new Genealogico();
        g.insertarAncestro("Amelia", false);
        g.insertar("Edinson", "Amelia", true);
        g.insertar("Luz Amelia", "Amelia", false);
        g.insertar("David", "Edinson", true);
        g.insertar("Jose", "Amelia", true);
        // System.out.println(g.altura());w
    }
}
