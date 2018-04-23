public class Persona
{
    String nombre;
    boolean sexo;
    Persona hijo;
    Persona hija;
    
    Persona(String nombre, boolean sexo){
        this.nombre = nombre;
        this.sexo = sexo;
    }
}
