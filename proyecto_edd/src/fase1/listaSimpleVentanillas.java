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
}
