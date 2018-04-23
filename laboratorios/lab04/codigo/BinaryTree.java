public class BinaryTree {
    public Node root;

    public BinaryTree(){
        root = null;
    }

    void insertar(String s){
        insertar(root, s);
    }

    private void insertar(Node n, String s){
        if(root == null){root = new Node(s);}
        else{
            if(n.left == null){n.left = new Node(s);}
            else{
                if(n.right == null)n.right = new Node(s);
                else insertar(n.right, s);
            }
        }
    }

    private int max2(int i, int j)
    {
        if (i > j)
            return i;
        return j;
    }

    private int maxheightAUX(Node node)
    {
        if(node == null)
            return 0;
        else 
            return max2(maxheightAUX(node.left), maxheightAUX(node.right))+1;
    }

    public int maxheight()
    {
        return maxheightAUX(root);
    }

    private void recursivePrintAUX(Node node)
    {
        if(node != null){
            recursivePrintAUX(node.left);
            recursivePrintAUX(node.right);
            System.out.println(node.data);
        }   
    }

    public void recursivePrint()
    {
        recursivePrintAUX(root);
    }

    private boolean buscar(Node node, String s){
        if(node == null)return false;
        if(node.data.equals(s)){
            return true;
        }
        return buscar(node.left, s) || buscar(node.right, s);
    }

    public boolean buscar(String s){
        return buscar(root,s);
    }
    
    private String abuelaMaterna(Node n, String nieto){
        if(n == null){
            return "";
        }
        if(n.left.left.data.equals(nieto))return n.data;
        return abuelaMaterna(n.left, nieto) + abuelaMaterna(n.right, nieto);
    }

    void abuelaMaterna(String nieto){
        if(buscar(nieto)){
            System.out.println(abuelaMaterna(root, nieto)); 
        }
    }
}