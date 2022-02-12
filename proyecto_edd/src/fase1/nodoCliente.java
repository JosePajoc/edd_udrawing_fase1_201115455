package fase1;

public class nodoCliente {

    protected int id;
    protected String nombre;
    protected int img_color;
    protected int img_bw;
    protected int num_pasos;
    nodoCliente siguiente;

    public nodoCliente(int id, String nombre, int img_color, int img_bw) {
        this.id = id;
        this.nombre = nombre;
        this.img_color = img_color;
        this.img_bw = img_bw;
        this.num_pasos = 0;
        this.siguiente = null;
    }
}
