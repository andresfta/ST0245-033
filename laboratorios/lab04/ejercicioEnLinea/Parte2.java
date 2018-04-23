import java.util.TreeSet;
import java.util.Scanner;
import java.util.ArrayList;
public class Parte2
{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Arbol a = new Arbol();
        while(sc.hasNextInt())a.insertar(sc.nextInt());
        a.imprimir();
    }
    // Pre - orden: 50 30 24 5 28 45 98 52 60 60�
    // Pos - orden: 5 28 24 45 30 60 52 98 50�
}
