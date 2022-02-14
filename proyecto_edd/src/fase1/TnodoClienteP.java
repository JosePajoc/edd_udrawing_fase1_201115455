package fase1;

public class TnodoClienteP {

    //Tarjeta del nodo cliente para la pila
    protected int id;
    protected String nombre;
    protected String tipoImg;
    TnodoClienteP siguiente;

    public TnodoClienteP(int id, String nombre, String tipoImg) {
        this.id = id;
        this.nombre = nombre;
        this.tipoImg = tipoImg;
        this.siguiente = null;
    }
}
