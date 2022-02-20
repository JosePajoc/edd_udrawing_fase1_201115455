package fase1;

public class nodoImgImpresa {
    String tipoImg;
    nodoImgImpresa siguiente;
    
    //Nodo de tipo imagen para ser agregada al cliente que esta en la sala de espera
    public nodoImgImpresa(String tipoImg){
        this.tipoImg = tipoImg;
        this.siguiente = null;
    }
}
