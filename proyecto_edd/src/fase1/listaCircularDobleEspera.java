package fase1;

public class listaCircularDobleEspera {
    
    nodoClienteEspera inicio;
    
    public listaCircularDobleEspera(){
        this.inicio = null;
    }
    
    public boolean verVacio(){
        return this.inicio == null;
    }
    
    public void insertarClienteEspera(int id, String nombre){
        nodoClienteEspera nuevoNodo = new nodoClienteEspera(id, nombre);
        if(this.verVacio()){
            this.inicio = nuevoNodo;
            this.inicio.siguiente = nuevoNodo;
            this.inicio.anterior = nuevoNodo;
        }else{
            nodoClienteEspera auxiliar = this.inicio;
            while(auxiliar.siguiente != this.inicio){
                auxiliar = auxiliar.siguiente;
            }
            auxiliar.siguiente = nuevoNodo;
            nuevoNodo.siguiente = this.inicio;
            this.inicio.anterior = nuevoNodo;
            nuevoNodo.anterior = auxiliar;
        }
    }
    
    public String verListaCircularDobleEspera(){
        String cadena = "";
        if(!this.verVacio()){
            nodoClienteEspera auxiliar = this.inicio;
            do{
                cadena = cadena + auxiliar.nombre + "|-|";
                auxiliar = auxiliar.siguiente;
            }while(auxiliar != this.inicio);
            return cadena;
        }else{
            return "No hay clientes aún en sala de espera...";
        }
        
    }
}
