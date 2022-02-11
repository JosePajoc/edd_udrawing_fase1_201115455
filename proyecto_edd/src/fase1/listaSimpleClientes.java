package fase1;

public class listaSimpleClientes {
    nodoCliente inicio;
    //Creando lista vacía con cabecera
    public listaSimpleClientes(){
        this.inicio = null;
    }
    
    public boolean verVacio(){
        return this.inicio == null;
    }
    
    public void insertarNodoCliente(int id, String nombre, int img_color, int img_bw){
        nodoCliente nuevoNodoCliente = new nodoCliente(id, nombre, img_color, img_bw);
        if(this.verVacio()){
            inicio = nuevoNodoCliente;
        }else{
            nodoCliente nodoAuxiliar = this.inicio;
            while(nodoAuxiliar.siguiente != null){
                nodoAuxiliar = nodoAuxiliar.siguiente;
            }
            nodoAuxiliar.siguiente = nuevoNodoCliente;
        }
    }
    
    public void verNodosClientes(){
        nodoCliente nodoAuxiliar = this.inicio;
        do{
            System.out.println(nodoAuxiliar.id + ", " + nodoAuxiliar.nombre + ", " + nodoAuxiliar.img_color + ", " + nodoAuxiliar.img_bw);
            nodoAuxiliar = nodoAuxiliar.siguiente;
        }while(nodoAuxiliar != null);
    }
}
