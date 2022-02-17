package fase1;

public class colaImpColor {

    TnodoClienteP inicio;

    public colaImpColor() {
        this.inicio = null;
    }

    public boolean verVacio() {
        return this.inicio == null;
    }

    public void insertarImagenCola(int id, String nombre, String tipoImg) {
        TnodoClienteP nuevaImpresion = new TnodoClienteP(id, nombre, tipoImg);
        if (this.verVacio()) {
            this.inicio = nuevaImpresion;
        } else {
            TnodoClienteP nodoAuxiliar = this.inicio;
            while (nodoAuxiliar.siguiente != null) {
                nodoAuxiliar = nodoAuxiliar.siguiente;
            }
            nodoAuxiliar.siguiente = nuevaImpresion;
        }
    }

    public String verColaImpColor() {
        TnodoClienteP nodoAuxiliar = this.inicio;
        String datosColaImp = "";
        if (this.verVacio()) {
            return "La impresora a color esta vacía";
        } else {
            while (nodoAuxiliar != null) {
                datosColaImp = datosColaImp + nodoAuxiliar.nombre + ", " + nodoAuxiliar.tipoImg + "|.|";
                nodoAuxiliar = nodoAuxiliar.siguiente;
            }
            return datosColaImp;
        }
    }

    public TnodoClienteP sacarImpresionCola() {
        TnodoClienteP nodoAuxiliar = this.inicio;
        this.inicio = this.inicio.siguiente;
        return nodoAuxiliar;
    }

    public int verTamanioCola() {
        int tam = 0;
        if (this.verVacio()) {
            return 0;
        } else {
            TnodoClienteP nodoAuxiliar = this.inicio;
            while (nodoAuxiliar != null) {
                tam++;
                nodoAuxiliar = nodoAuxiliar.siguiente;
            }
            return tam;
        }
    }
}
