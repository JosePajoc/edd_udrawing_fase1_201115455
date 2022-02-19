package fase1;

public class pilaImg {

    TnodoClienteP inicio;

    //Creando pila vacía con cabecera
    public pilaImg() {
        this.inicio = null;
    }

    public boolean verVacio() {
        return this.inicio == null;
    }

    public void apilarNodoCliente(int id, String nombre, String tipoImg) {
        TnodoClienteP nuevoNodoCliente = new TnodoClienteP(id, nombre, tipoImg);
        if (this.verVacio()) {
            inicio = nuevoNodoCliente;
        } else {
            TnodoClienteP nodoAuxiliar = this.inicio;
            this.inicio = nuevoNodoCliente;
            this.inicio.siguiente = nodoAuxiliar;
        }
    }

    public void desapilarNodoCliente() {
        if (this.verVacio()) {
            System.out.println("No hay datos en la pila");
        } else {
            this.inicio = this.inicio.siguiente;
        }
    }

    public String verNodoClientesApilado() {
        TnodoClienteP nodoAuxiliar = this.inicio;
        String datosPila = "";
        while (nodoAuxiliar != null) {
            datosPila = datosPila + nodoAuxiliar.nombre + ", " + nodoAuxiliar.tipoImg + "|.|";
            nodoAuxiliar = nodoAuxiliar.siguiente;
        }
        return datosPila;
    }
    
    public int cantidadImagenesApiladas(){
        TnodoClienteP nodoAuxiliar = this.inicio;
        int cantidad = 0;
        while (nodoAuxiliar != null) {
            cantidad++;
            nodoAuxiliar = nodoAuxiliar.siguiente;
        }
        return cantidad;
    }
    
    
}
