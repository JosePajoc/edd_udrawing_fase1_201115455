package fase1;

public class nodoClienteEspera {
    int id;
    String nombre;
    //listaImgImpresas imagenes;
    nodoClienteEspera siguiente;
    nodoClienteEspera anterior;
    
    public nodoClienteEspera(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
        this.siguiente = null;
        this.anterior = null;
    }
}
