import java.util.*;
public class Taller8
{
    static Stack<Integer> punto1(){
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < 3; i++)queue.add(stack.pop());
        for(int i = 0; i < 3; i++)stack.push(queue.poll());
        return stack;
    }
    
    static void punto2(){
        Queue<String> queue = new LinkedList<String>();
        queue.add("Juan");
        queue.add("María");
        queue.add("Pedro");
        for(int i = 0; i < 3; i++)System.out.println("Atiendo a: " + queue.poll());
    }
    
    static void punto3(){
        Scanner s = new Scanner(System.in);
        String p = s.nextLine();
        p = p.trim();
        String[] c =  p.split(" ");
        // "3 5 * 2 +"
        Stack<Integer> t = new Stack();
        for(String y: c){
            if(Character.isDigit(y.charAt(0)))t.push(Integer.parseInt(y));
            else{
                int a = t.pop(), b = t.pop(), d = 0;
                if(y.charAt(0) == '+')d = a + b;
                if(y.charAt(0) == '-')d = b - a;
                if(y.charAt(0) == '*')d = a * b;
                if(y.charAt(0) == '/')d = b / a;
                t.push(d);
            }
        }
        System.out.println(t);
    }
}
