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
    
}
