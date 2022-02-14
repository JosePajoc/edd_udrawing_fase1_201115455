package fase1;

public class listaSimpleVentanillas {
   nodoVentanilla inicio;

    //Creando lista vacía con cabecera
    public listaSimpleVentanillas() {
        this.inicio = null;
    } 
    
    public boolean verVacio() {
        return this.inicio == null;
    }
    
    public void insertarNodoVentanilla(int id) {
        nodoVentanilla nuevoNodoVentanilla = new nodoVentanilla(id);
        if (this.verVacio()) {
            this.inicio = nuevoNodoVentanilla;
        } else {
            nodoVentanilla nodoAuxiliar = this.inicio;
            while (nodoAuxiliar.siguiente != null) {
                nodoAuxiliar = nodoAuxiliar.siguiente;
            }
            nodoAuxiliar.siguiente = nuevoNodoVentanilla;
        }
    }
    
    public void verNodosVentanillas() {
        nodoVentanilla nodoAuxiliar = this.inicio;
        do {
            System.out.println("--> " + nodoAuxiliar.id);
            nodoAuxiliar = nodoAuxiliar.siguiente;
        } while (nodoAuxiliar != null);
    }
    
    public boolean verVentanillaDisponible(){
        nodoVentanilla nodoAuxiliar = this.inicio;
        while ((nodoAuxiliar != null) && (nodoAuxiliar.habilitado == false)){
            nodoAuxiliar = nodoAuxiliar.siguiente;
        }
        if((nodoAuxiliar != null) && (nodoAuxiliar.habilitado == true)){
            return true;
        }else{
            return false;
        }
    }
    
    public String atenderCliente(nodoCliente cliente){
        nodoVentanilla nodoAuxiliar = this.inicio;
        while ((nodoAuxiliar != null) && (nodoAuxiliar.habilitado == false)){
            nodoAuxiliar = nodoAuxiliar.siguiente;
        }
        if((nodoAuxiliar != null) && (nodoAuxiliar.habilitado == true)){
            nodoAuxiliar.cliente = cliente;
            nodoAuxiliar.habilitado = false;
            return "Cliente atendido en la ventanilla -> " + nodoAuxiliar.id;
        }else{
            return "############################## Ventanillas ocupadas ##############################\n";
        }   
    }
    
    
    
    public void ver(){
        nodoVentanilla nodoAuxiliar = this.inicio;
        do {
            System.out.println("Ventanilla -> " + nodoAuxiliar.id + " -> Cliente que atiende -> " + nodoAuxiliar.cliente.nombre);
            nodoAuxiliar = nodoAuxiliar.siguiente;
        } while ((nodoAuxiliar) != null && (nodoAuxiliar.cliente != null));
        System.out.println("");
    }
}
