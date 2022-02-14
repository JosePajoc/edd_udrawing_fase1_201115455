package fase1;

public class pilaImg {
    nodoCliente inicio;

    //Creando pila vacía con cabecera
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

    public String verNodoClientesApilado() {
        nodoCliente nodoAuxiliar = this.inicio;
        String datosPila = "";
        while (nodoAuxiliar != null){
            datosPila = datosPila + nodoAuxiliar.id + ", " + nodoAuxiliar.nombre + ", " + nodoAuxiliar.img_color + ", " + nodoAuxiliar.img_bw + "||";
            nodoAuxiliar = nodoAuxiliar.siguiente;
        }
        return datosPila;
    }
}
