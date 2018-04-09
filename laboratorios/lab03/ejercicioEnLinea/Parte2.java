import java.util.Scanner;
import java.util.LinkedList;
public class Parte2
{
    private static String depure(String s){
        if(s.length() < 2)return s;
        if(s.startsWith("[[") || s.startsWith("[]") || s.startsWith("][") || s.startsWith("]]"))return depure(s.substring(1));
        return s.charAt(0) + depure(s.substring(1));
    }

    static void punto2(){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        s = depure(s);
        while(true){if(s.endsWith("]") || s.endsWith("["))s = s.substring(0,s.length() - 1); else break;}
        int keys = 0;
        for(int i = 0; i < s.length(); i++)if(s.charAt(i) == '[' || s.charAt(i) == ']')keys++;
        LinkedList<String> partes = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
            String parte = "";
            String key = "";
            for(int j = i; j < s.length(); j++){
                if(s.charAt(j) != '[' && s.charAt(j) != ']'){parte += s.charAt(j); i++;}
                else{key = (s.substring(j,j+1)); break;}
            }
            partes.add(parte);
            if(key.length() != 0)partes.add(key);
        }
        for(int i = 0; i < keys; i++){
            for(int j = 0; j < partes.size(); j++){
                if(partes.get(j).equals("[")){
                    partes.add(0, partes.get(j + 1));
                    partes.remove(j + 1);
                    partes.remove(j + 1);
                    keys--;
                }
                if(partes.get(j).equals("]")){
                    partes.add(partes.get(j+1));
                    partes.remove(j + 1);
                    partes.remove(j);
                    keys--;
                }
            }
        }
        String sucess = "";
        for(int i = 0; i < partes.size(); i++)sucess += partes.get(i);
        System.out.println(sucess);
    }

    static void punto3(){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            int a = sc.nextInt(), b = sc.nextInt(), count = 0;
            for(int j = a - 1; j < b - 1; j++){
                char ac = s.charAt(j), bc = s.charAt(j+1);
                if(ac == bc)count++;
            }
            System.out.println(count);
        }        
    }
    
    private static String depureKeys(String s){
        if(s.length() < 2)return s;
        if(s.startsWith("()"))return depureKeys(s.substring(2));
        if(s.startsWith("[]"))return depureKeys(s.substring(2));
        if(s.startsWith("{}"))return depureKeys(s.substring(2));
        return s.charAt(0) + depureKeys(s.substring(1));
    }
    
    static void punto4(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            String s = sc.next();
            boolean b = s.contains("()") || s.contains("[]") || s.contains("{}");
            while(b){s = depureKeys(s); b = s.contains("()") || s.contains("[]") || s.contains("{}");}            
            String r = "YES";
            if(s.length() != 0)r = "NO";
            System.out.println(r);
        }
    }
}
