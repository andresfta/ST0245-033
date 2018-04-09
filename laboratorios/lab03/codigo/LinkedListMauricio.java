import java.lang.IndexOutOfBoundsException; // Usar esto cuando se salga el índice
// Una lista simplemente enlazada
public class LinkedListMauricio {
    private Node first;
    private int size;
    public LinkedListMauricio()
    {
        size = 0;
        first = null;   
    }

    /**
     * Returns the node at the specified position in this list.
     * @param index - index of the node to return
     * @return the node at the specified position in this list
     * @throws IndexOutOfBoundsException
     */
    private Node getNode(int index) throws IndexOutOfBoundsException {
        if(index >= 0 && index < size) {
            Node temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.next;   
            }
            return temp;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index - index of the element to return
     * @return the element at the specified position in this list
     */
    public int get(int index) {
        Node temp = null;
        try {
            temp = getNode(index);
        }catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return temp.data;
    }

    // Retorna el tamaño actual de la lista
    public int size(){
        return size;
    }

    // Inserta un dato en la posición index
    public void insert(int data, int index){
        Node nuevo = new Node(data);
        if(size == 0){first = nuevo; first.next = null; size++;}
        else{
            if(index == 0){
                nuevo.next = first;
                first = nuevo;
            }else{
                Node temp = first;
                for(int i = 0; i < index - 1; i++){
                    temp = temp.next;
                }
                Node next = temp.next;
                temp.next = nuevo;
                nuevo.next = next;
            }
            size++;
        }
    }

    // Borra el dato en la posición index
    public void remove(int index){
        if(size == 0){System.out.println("Empty list"); return;}
        if(index == 0 && size == 1){
            first = null;
            size--;
        }
        else
        if(size >= index){
            Node temp = first;
            for(int i = 0; i < index - 1; i++){
                temp = temp.next;
            }
            Node next = temp.next;
            temp.next = next.next;
            next.next = null;
            size--;
        }
    }

    // Verifica si está un dato en la lista
    public boolean contains(int data)
    {
        if(size != 0){
            Node temp = first;
            while(temp != null){if(temp.data == data)return true; temp = temp.next;}
        }
        return false;
    }

    public String toString(){
        String s = "" + first.data;
        Node temp = first.next;
        for(int i = 0; i < size - 1; i++){s += "\n" + temp.data; temp = temp.next;}
        return s;
    }

    public static void main(String[] args){
        LinkedListMauricio list = new LinkedListMauricio();
        list.insert(5, 0);
        list.insert(4, 0);
        list.insert(3, 0);
        list.insert(2, 0);
        list.insert(1, 0);
        for (int i = 0; i < list.size(); i++){System.out.println(list.get(i));}
        System.out.println(list.contains(3));   
        list.remove(list.size() - 1);
        System.out.println(list.contains(3));
        for (int i = 0; i < list.size(); i++){System.out.println(list.get(i));}
    }
}