import java.util.HashMap;
public class Taller9{
    //pedrito 2
    public static void agregar(HashMap empresas,String key, String value){
        empresas.put(key, value);
    }   
    //pedrito 3
    public static void buscar(HashMap empresas,String key){
        if(empresas.containsKey(key))System.out.println(key + "→" + empresas.get(key));
        else System.out.println("Object not found");
    }
    //pedrito 4
    public static boolean contienekey(HashMap empresas, String value){
        return empresas.containsValue(value);
    } 
}
