package fase1;

public class listaCircularDobleEspera {

    nodoClienteEspera inicio;

    public listaCircularDobleEspera() {
        this.inicio = null;
    }

    public boolean verVacio() {
        return this.inicio == null;
    }

    public void insertarClienteEspera(int id, String nombre, int imgTot) {
        nodoClienteEspera nuevoNodo = new nodoClienteEspera(id, nombre, imgTot);
        if (this.verVacio()) {
            this.inicio = nuevoNodo;
            this.inicio.siguiente = nuevoNodo;
            this.inicio.anterior = nuevoNodo;
        } else {
            nodoClienteEspera auxiliar = this.inicio;
            while (auxiliar.siguiente != this.inicio) {
                auxiliar = auxiliar.siguiente;
            }
            auxiliar.siguiente = nuevoNodo;
            nuevoNodo.siguiente = this.inicio;
            this.inicio.anterior = nuevoNodo;
            nuevoNodo.anterior = auxiliar;
        }
    }

    public String verListaCircularDobleEspera() {
        String cadena = "";
        if (!this.verVacio()) {
            nodoClienteEspera auxiliar = this.inicio;
            do {
                cadena = cadena + auxiliar.nombre + "|-|";
                auxiliar = auxiliar.siguiente;
            } while (auxiliar != this.inicio);
            return cadena;
        } else {
            return "No hay clientes aún en sala de espera...";
        }
    }

    public void entregarImpCliente(int id, String img) {
        if (!this.verVacio()) {
            nodoClienteEspera aux = this.inicio;
            do {
                if (aux.id == id) {
                    aux.imagenes.insertarImg(img);
                    aux.imgEntregadas++;
                    System.out.println("La imagen impresa a sido entregada a " + aux.nombre);
                }
                aux = aux.siguiente;
            } while (aux != this.inicio);

            //this.salirClienteEspera();
        }
    }

//    public void salirClienteEspera() {
//        if (!this.verVacio()) {
//            nodoClienteEspera aux = this.inicio;
//            if (this.inicio.imgEntregadas == this.inicio.totImg) {
//                nodoClienteEspera temp = aux.siguiente;
//                while (temp.siguiente != this.inicio) {  //recorrer la lista circular doble hasta llegar uno antes de la cabecera
//                    temp = temp.siguiente;
//                }
//                System.out.println("--->> Se va " + aux.nombre);
//                if (this.verTamListaCircularDobleEspera() == 1) { //Si se cumple todas las entregas y solo hay un cliente esperando
//                    this.inicio = null;
//                } else {
//                    temp.siguiente = this.inicio.siguiente;
//                    this.inicio.siguiente.anterior = temp;
//                    this.inicio = temp;
//                }
//            }else{
//                aux = this.inicio.siguiente;
//                while (aux.siguiente != this.inicio && aux.totImg != aux.imgEntregadas) {  //recorrer la lista circular doble hasta llegar uno antes de la cabecera
//                    aux = aux.siguiente;
//                }
//                System.out.println("--->> Se va " + aux.nombre);
//                aux.anterior.siguiente = aux.siguiente;
//                aux.siguiente.anterior = aux.anterior;
//            }
//        }
//    }

    public String datosDotEspera() {
        String nodos = "";
        String union = "";
        int valor = 0;
        if (!this.verVacio()) {
            nodoClienteEspera auxiliar = this.inicio;
            if (this.verTamListaCircularDobleEspera() == 1) {
                return "node" + valor + "[label=\"" + auxiliar.nombre + "\"]; \n" + "node" + valor + " -> " + "node" + valor + ";";
            } else {
                do {
                    nodos = nodos + "node" + valor + "[label=\"" + auxiliar.nombre + "\"]; \n";
                    if (auxiliar.siguiente == this.inicio) {
                        union = union + "node" + valor + " -> node0" + "; \n";
                        union = union + "node0 -> " + "node" + valor + "[label = n]; \n\n";
                    } else {
                        union = union + "node" + valor + " -> " + "node" + (valor + 1) + "; \n";
                        union = union + "node" + (valor + 1) + " -> " + "node" + valor + "; \n\n";
                    }
                    valor++;
                    auxiliar = auxiliar.siguiente;
                } while (auxiliar != this.inicio);
                return nodos + union;
            }
        } else {
            return "No hay clientes aún en sala de espera...";
        }
    }

    public int verTamListaCircularDobleEspera() {
        int cantidad = 0;
        if (!this.verVacio()) {
            nodoClienteEspera auxiliar = this.inicio;
            do {
                cantidad++;
                auxiliar = auxiliar.siguiente;
            } while (auxiliar != this.inicio);
            return cantidad;
        } else {
            return 0;
        }
    }
}
