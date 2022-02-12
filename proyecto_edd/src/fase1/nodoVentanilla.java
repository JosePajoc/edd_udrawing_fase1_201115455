package fase1;

public class nodoVentanilla {
    protected int id;
    protected boolean habilitado;
    protected pilaImg pila_img;
    nodoVentanilla siguiente;
    
    public nodoVentanilla(int id){
        this.id = id;
        this.habilitado = true;
        this.pila_img = new pilaImg();
        this.siguiente = null;
        
    }
}


