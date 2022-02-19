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
        //Se recorren todas las ventanillas
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
                    System.out.println("--------------- El cliente termino de entregar sus imagenes en la ventanilla No. " + nodoAuxiliarV.id);
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
    
    //datos para creal el grafo
//    public String datosVentanillas(){
//        String cadena = "newrank=true; \n";
//        String unionNodos = "";
//        String unionVentanillas = "";
//        String direccion = "";
//        
//        nodoVentanilla nodoAux = this.inicio;
//        while(nodoAux != null){
//            cadena = cadena +  "nodoClie" + nodoAux.cliente.id + "[label=\"" + nodoAux.cliente.nombre + ", \n img_color = "
//                    + nodoAux.cliente.img_color + ", \n img_bw = " + nodoAux.cliente.img_bw + "\"]; \n"
//                    + "nodeVent" + nodoAux.id + "[label=\"Ventanilla No. " + nodoAux.id + "\"]; \n" ;
//            
//            unionNodos = unionNodos + "nodoClie" + nodoAux.cliente.id + " -> " + "nodeVent" + nodoAux.id + "; \n";
//            if(nodoAux.siguiente != null){
//                unionVentanillas = unionVentanillas +  "nodeVent" + nodoAux.id + " -> " +  "nodeVent" + (nodoAux.id + 1) + " \n";
//            
//            }
//            
//            direccion = direccion + "nodeVent" + nodoAux.id + "; ";
//            
//            nodoAux = nodoAux.siguiente;
//        }
//        return cadena + unionNodos + unionVentanillas + " \n { rank=same; " + direccion + " } \n";
//    }
}
