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

    public boolean verVentanillaDisponible() {
        nodoVentanilla nodoAuxiliar = this.inicio;
        while ((nodoAuxiliar != null) && (nodoAuxiliar.habilitado == false)) {
            nodoAuxiliar = nodoAuxiliar.siguiente;
        }
        if ((nodoAuxiliar != null) && (nodoAuxiliar.habilitado == true)) {
            return true;
        } else {
            return false;
        }
    }

    public String atenderCliente(nodoCliente cliente) {
        nodoVentanilla nodoAuxiliar = this.inicio;
        while ((nodoAuxiliar != null) && (nodoAuxiliar.habilitado == false)) {
            nodoAuxiliar = nodoAuxiliar.siguiente;
        }
        if ((nodoAuxiliar != null) && (nodoAuxiliar.habilitado == true)) {
            nodoAuxiliar.cliente = cliente;
            nodoAuxiliar.habilitado = false;
            return "Cliente atendido en la ventanilla -> " + nodoAuxiliar.id;
        } else {
            return "############################## Ventanillas ocupadas ##############################\n";
        }
    }

    public void recepcionImg() {
        nodoVentanilla nodoAuxiliarV = this.inicio;
        while ((nodoAuxiliarV != null)) {

            if (nodoAuxiliarV.cliente != null) {
                if (nodoAuxiliarV.cliente.img_bw > 0) {
                    nodoAuxiliarV.pila_img.apilarNodoCliente(nodoAuxiliarV.cliente.id, nodoAuxiliarV.cliente.nombre, "img_bw");
                    nodoAuxiliarV.cliente.img_bw--;
                } else if (nodoAuxiliarV.cliente.img_color > 0) {
                    nodoAuxiliarV.pila_img.apilarNodoCliente(nodoAuxiliarV.cliente.id, nodoAuxiliarV.cliente.nombre, "img_color");
                    nodoAuxiliarV.cliente.img_color--;
                }
                System.out.println("Pila, ventanilla No. " + nodoAuxiliarV.id + " -> " + nodoAuxiliarV.pila_img.verNodoClientesApilado());
                if ((nodoAuxiliarV.cliente.img_bw == 0) && (nodoAuxiliarV.cliente.img_color == 0)) {
                    System.out.println("################### El cliente termino de entregar sus imagenes en la ventanilla No. " + nodoAuxiliarV.id);
                    nodoAuxiliarV.recepcionFin = true;
                    
                }
            }
            nodoAuxiliarV = nodoAuxiliarV.siguiente;         
        }
    }
    
    public nodoVentanilla enviarImpresion() {
        nodoVentanilla nodoAuxiliar = this.inicio;
        while ((nodoAuxiliar != null) && (nodoAuxiliar.recepcionFin == false)) {
            nodoAuxiliar = nodoAuxiliar.siguiente;
        }
        if ((nodoAuxiliar != null) && (nodoAuxiliar.recepcionFin == true)) {
            return nodoAuxiliar;
        }else{
            return null;
        }
    }

    public void ver() {
        nodoVentanilla nodoAuxiliar = this.inicio;
        do {
            System.out.println("Ventanilla -> " + nodoAuxiliar.id + " -> Cliente que atiende -> " + nodoAuxiliar.cliente.nombre);
            nodoAuxiliar = nodoAuxiliar.siguiente;
        } while ((nodoAuxiliar) != null && (nodoAuxiliar.cliente != null));
        System.out.println("");
    }
}
