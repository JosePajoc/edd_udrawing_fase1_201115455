package fase1;

public class colaImpBW {

    TnodoClienteP inicio;
    
    public colaImpBW(){
        this.inicio = null;
    }
    
    public boolean verVacio(){
        return this.inicio == null;
    }
    
    public void insertarImagenCola(int id, String nombre, String tipoImg){
        TnodoClienteP nuevaImpresion = new TnodoClienteP(id, nombre, tipoImg);
        if(this.verVacio()){
            this.inicio = nuevaImpresion;
        }else{
            TnodoClienteP nodoAuxiliar = this.inicio;
            while(nodoAuxiliar.siguiente != null){
                nodoAuxiliar = nodoAuxiliar.siguiente;
            }
            nodoAuxiliar.siguiente = nuevaImpresion;
        }
    }
    
    public String verColaImpBW(){
        TnodoClienteP nodoAuxiliar = this.inicio;
        String datosColaBW = "";
        if(this.verVacio()){
            return "La impresora blanco y negro esta vacía";
        }else{
            while(nodoAuxiliar != null){
            datosColaBW = datosColaBW + nodoAuxiliar.nombre + ", " + nodoAuxiliar.tipoImg + "|.|";
            nodoAuxiliar = nodoAuxiliar.siguiente;
        }
        return datosColaBW;
        } 
    }
    
}
