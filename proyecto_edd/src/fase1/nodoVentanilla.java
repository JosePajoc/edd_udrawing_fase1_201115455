package fase1;

public class nodoVentanilla {
    protected int id;
    protected boolean habilitado;
    protected pilaImg pila_img;
    protected nodoCliente cliente;
    nodoVentanilla siguiente;
    
    public nodoVentanilla(int id){
        this.id = id;
        this.habilitado = true;
        this.pila_img = new pilaImg();
        this.cliente = null;
        this.siguiente = null;
        
    }
}


