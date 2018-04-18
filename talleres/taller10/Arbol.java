class Arbol {
    private Nodo raiz;
    private void imprimir(Nodo nodo){
        if (nodo != null){
            imprimir(nodo.izq);
            System.out.println(nodo.dato);
            imprimir(nodo.der);
        }
    }

    public void imprimir(){
        imprimir(raiz);
    }

    private void insertar(Nodo nodo, int n){            
        if (nodo.dato == n)
            return ;
        if (nodo.dato > n)
            if (nodo.der == null)
                nodo.der = new Nodo(n);
            else
                insertar(nodo.der, n);
        else
        if (nodo.izq == null)
            nodo.izq = new Nodo(n);
        else
            insertar(nodo.izq, n);   
    }

    public void insertar(int n){
        if (raiz == null)raiz = new Nodo(n);
        else insertar(raiz, n);
    }

    private boolean buscar(Nodo nodo, int n){
        if(nodo == null)return false;
        if(nodo.dato == n)return true;
        if(nodo.dato > n)return buscar(nodo.der, n);
        return buscar(nodo.izq, n);             
    }

    public boolean buscar(int n){
        return buscar(raiz,n);
    }
    
    private void borrar(Nodo nodo, int n){
        if(nodo.dato == n){
            if(nodo.dato > nodo.padre.dato){nodo.padre.der = nodo.der;}
            if(nodo.dato < nodo.padre.dato){nodo.padre.izq = nodo.der;}
        }
    }
    
    void borrar(int n){
        if(buscar(n)){
            borrar(raiz, n);
        }
    }
}
class Nodo {
    public Nodo(int n){dato = n;}
    public int dato;
    public Nodo izq;
    public Nodo der;
    public Nodo padre;
}
