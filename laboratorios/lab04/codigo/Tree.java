import java.util.TreeSet;
public class Tree
{
    public static void main(String[] args){
        TreeSet<String> t = new TreeSet<>();
        t.add("Rosa");
        t.add("Amelia");
        t.add("Luz");
        t.add("Jamer");
        System.out.println(t.contains("Jamer"));
        System.out.println(t.size());
        System.out.println(t);
    }
}
