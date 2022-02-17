package fase1;

public class nodoClienteEspera {
    int id;
    String nombre;
    int totImg;
    int imgEntregadas;
    listaImgImpresas imagenes;
    nodoClienteEspera siguiente;
    nodoClienteEspera anterior;
    
    public nodoClienteEspera(int id, String nombre, int imgTot){
        this.id = id;
        this.nombre = nombre;
        this.totImg = imgTot;
        this.imgEntregadas = 0;
        imagenes = new listaImgImpresas();
        this.siguiente = null;
        this.anterior = null;
    }
}
