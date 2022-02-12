package fase1;

public class pilaImg {
    nodoCliente inicio;

    //Creando lista vacía con cabecera
    public pilaImg() {
        this.inicio = null;
    }

    public boolean verVacio() {
        return this.inicio == null;
    }

    public void apilarNodoCliente(int id, String nombre, int img_color, int img_bw) {
        nodoCliente nuevoNodoCliente = new nodoCliente(id, nombre, img_color, img_bw);
        if (this.verVacio()) {
            inicio = nuevoNodoCliente;
        } else {
            nodoCliente nodoAuxiliar = this.inicio;
            this.inicio = nuevoNodoCliente;
            this.inicio.siguiente = nodoAuxiliar;
        }
    }
    
    public void desapilarNodoCliente(){
        if(this.verVacio()){
            System.out.println("No hay datos en la pila");
        }else{
            this.inicio = this.inicio.siguiente;
        }
    }

    public void verNodosClientes() {
        nodoCliente nodoAuxiliar = this.inicio;
        while (nodoAuxiliar != null){
            System.out.println(nodoAuxiliar.id + ", " + nodoAuxiliar.nombre + ", " + nodoAuxiliar.img_color + ", " + nodoAuxiliar.img_bw);
            nodoAuxiliar = nodoAuxiliar.siguiente;
        }
    }
}
