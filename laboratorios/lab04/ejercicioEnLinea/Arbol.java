class Arbol {
    private Nodo raiz;
    private void imprimir(Nodo nodo){
        if(nodo != null){
            System.out.println(nodo.dato);            
            imprimir(nodo.izq);
            imprimir(nodo.der);
        }
    }

    private void imprimirPos(Nodo nodo){
        if(nodo.padre == raiz){}
        else
        if(nodo != null){
            if(nodo.izq != null)System.out.println(nodo.izq.dato); if(nodo.der != null)System.out.println(nodo.der.dato);
            if(nodo.padre != null){
                imprimirPos(nodo.padre);
            }
        }
    }

    public void imprimir(){
        // imprimir(raiz);
        Nodo temp = raiz;
        while(true){if(temp == null)break; if(temp.izq == null){imprimirPos(temp.padre); break;} temp = temp.izq;}
    }

    private void insertar(Nodo nodo, int n){            
        if(nodo.dato == n)
            return;
        if(nodo.dato < n)
            if(nodo.der == null){Nodo newNode = new Nodo(n); nodo.der = newNode; nodo.der.padre = nodo;}
            else{insertar(nodo.der, n);} 
        else{
            if(nodo.izq == null){Nodo newNode = new Nodo(n); nodo.izq = newNode; nodo.izq.padre = nodo;}   
            else insertar(nodo.izq, n);
        }
    }

    public void insertar(int n){
        if(raiz == null)raiz = new Nodo(n);
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
