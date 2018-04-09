import java.util.*;
public class Parte1
{
    static int multiply(List<Integer> l){       // ArrayList     LinkedList
        int n = 1;                              // C1            C1
        while(!l.isEmpty())n *= l.remove(0);    // n * 1         n * 1
        return n;                               // C2            C2
    }                                           // O(n)          O(n)                                               

    static void SmartInsert(List<Integer> l, int data){     // ArrayList     LinkedList
        if(l.indexOf(data) == -1)l.add(data);               // n * 1 + (1)   n * 1 + (1)
    }                                                       // O(n)          // O(n)

    static void punto3(List<Integer> l){                    // ArrayList      LinkedList
        int min = Integer.MAX_VALUE, optime = 0;            //          C1 + C2
        for(int i = 0; i < l.size(); i++){                  //          n *
            int left = 0, rigth = 0;                        //              C3 + C4
            for(int j = 0; j < l.size(); j++){              //              n *
                if(j < i)left += l.get(j);                  //              C5
                if(j > i)rigth += l.get(j);                 //              C6
            }
            if(Math.abs(rigth - left) < min){min = rigth - left; optime = i;}   // C7
        }
        System.out.println(optime);                         // C8
    }                                                       //           O(n ^ 2)

    static void punto4(List<Nevera> inventario, List<Solicitud> s){                                 // ArrayList     LinkedList
        String entregas = "[";                                                                      //            C1           
        boolean primera = false;                                                                    //            C2    
        while(true){                                                                                //            M *                                                                                    
            Solicitud f = s.remove(s.size() - 1);                                                   //              n
            int n = f.number;                                                                       //              C3
            Entrega e = new Entrega(f);                                                             //              C4
            do{                                                                                     //              n *
                if(!inventario.isEmpty())e.addNevera(inventario.remove(inventario.size() - 1));     //                n
                else break;                                                                         
            }while(e.number < n);
            if(!primera){entregas += e.toString(); primera = true;}
            else entregas += "\n" + e.toString();
            if(s.isEmpty() || inventario.isEmpty())break;
        }
        entregas += "]";
        System.out.println(entregas);
    }                                                                                               // O(M * n^2)

    static void punto7(LinkedList<Cliente> a, LinkedList<Cliente> b, LinkedList<Cliente> c, LinkedList<Cliente> d){
        Banco.cajero(a,b,c,d);
    }

    public static void main(String[] args){
        LinkedList<Integer> linked = new LinkedList<>();
        linked.addAll(Arrays.asList(new Integer[] {1, 3, 5, 7}));

        ArrayList<Integer> array = new ArrayList<>();
        array.addAll(Arrays.asList(new Integer[] {2, 4, 6, 8}));

        // Multiply

        // System.out.println(multiply(linked));
        // System.out.println(multiply(array));

        // Punto 3

        LinkedList<Integer> l = new LinkedList<>();
        l.addAll(Arrays.asList(new Integer[] {10, 20, 5, 3, 10, 20}));

        ArrayList<Integer> a = new ArrayList<>();
        a.addAll(Arrays.asList(new Integer[] {10, 2, 4, 8}));

        // punto3(l);
        // punto3(a);

        // Punto 4

        LinkedList<Nevera> n = new LinkedList<>();
        n.addAll(Arrays.asList(new Nevera[] {new Nevera(1, "haceb"), new Nevera(2,"lg"), 
                    new Nevera(3,"ibm"), new Nevera(4,"haceb"), new Nevera(5,"lg"), 
                    new Nevera(6,"ibm"), new Nevera(7,"haceb"),new Nevera (8,"lg"), 
                    new Nevera(9,"ibm"), new Nevera(8,"lg"), new Nevera(9,"ibm")}));

        LinkedList<Solicitud> s = new LinkedList<>();
        s.addAll(Arrays.asList(new Solicitud[] {new Solicitud("eafit", 10), new Solicitud("la14", 2), 
                    new Solicitud("olimpica", 4), new Solicitud("éxito", 1)}));

        punto4(n,s);

        //  Punto 7

        LinkedList<Cliente> fila1 = new LinkedList<>();
        fila1.addAll(Arrays.asList(new Cliente[] {new Cliente("1 Cliente fila1"), new Cliente("2 Cliente fila1")}));
        LinkedList<Cliente> fila2 = new LinkedList<>();
        fila2.addAll(Arrays.asList(new Cliente[] {new Cliente("1 Cliente fila2")}));
        LinkedList<Cliente> fila3 = new LinkedList<>();
        fila3.addAll(Arrays.asList(new Cliente[] {new Cliente("1 Cliente fila3"), new Cliente("2 Cliente fila3"), new Cliente("3 Cliente fila3")}));
        LinkedList<Cliente> fila4 = new LinkedList<>();
        fila4.addAll(Arrays.asList(new Cliente[] {}));

        // punto7(fila1, fila2, fila3, fila4);
    }
}

class Nevera{
    int code;
    String description;

    Nevera(int code, String description){
        this.code = code;
        this.description = description;
    }

    public String toString(){
        return "(" + code + ", " + description + ")";
    }
}

class Solicitud{
    int number;
    String name;

    Solicitud(String name, int number){
        this.number = number;
        this.name = name;
    }

    public String toString(){
        return name;
    }
}

class Entrega{
    Solicitud s;
    LinkedList<Nevera> n = new LinkedList<>();
    int number = 0;

    Entrega(Solicitud s){
        this.s = s;
    }

    void addNevera(Nevera f){
        n.add(f); number++;
    }

    public String toString(){
        String t = "(" + s + ", [";
        for(Nevera f: n)t += f.toString() + ", ";
        return t.substring(0,t.length() - 2) + "])";
    }
}

class Cajero{
    LinkedList<Cliente> clientes = new LinkedList<>();
    
    void atenderCliente(Cliente c){clientes.add(c);}

    public String toString(){return clientes.toString();}
}

class Cliente{
    Cliente(String name){this.name = name;}    
    String name;
    public String toString(){return name;}
}

class Banco{
    static void cajero(LinkedList<Cliente> a, LinkedList<Cliente> b, LinkedList<Cliente> c, LinkedList<Cliente> d){
        Cajero c1 = new Cajero();
        Cajero c2 = new Cajero();
        boolean cajero = false;
        while(true){
            if(!a.isEmpty()){
                if(!cajero){c1.atenderCliente(a.pollFirst()); cajero = true;}
                else{c2.atenderCliente(a.pollFirst()); cajero = false;}
            }
            if(!b.isEmpty()){
                if(!cajero){c1.atenderCliente(b.pollFirst()); cajero = true;}
                else{c2.atenderCliente(b.pollFirst()); cajero = false;}
            }
            if(!c.isEmpty()){
                if(!cajero){c1.atenderCliente(c.pollFirst()); cajero = true;}
                else{c2.atenderCliente(c.pollFirst()); cajero = false;}
            }
            if(!d.isEmpty()){
                if(!cajero){c1.atenderCliente(d.pollFirst()); cajero = true;}
                else{c2.atenderCliente(d.pollFirst()); cajero = false;} 
            }
            if(a.isEmpty() && b.isEmpty() && c.isEmpty() && d.isEmpty())break;
        }
        System.out.println("Atendidos cajero 1: " + c1);
        System.out.println("Atendidos cajero 2: " + c2);
    }
}