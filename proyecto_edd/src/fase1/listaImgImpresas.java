package fase1;

public class listaImgImpresas {

    nodoImgImpresa inicio;

    public listaImgImpresas() {
        this.inicio = null;
    }

    public boolean verVacio() {
        return this.inicio == null;
    }

    public void insertarImg(String tipoImg) {
        nodoImgImpresa nuevaImg = new nodoImgImpresa(tipoImg);
        if (this.verVacio()) {
            this.inicio = nuevaImg;
        } else {
            nodoImgImpresa temp = this.inicio;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevaImg;
        }
    }
}
