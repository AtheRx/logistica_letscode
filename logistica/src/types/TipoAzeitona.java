package types;
public enum TipoAzeitona {
    GALEGA(4),
    CORDOVIL(3),
    PICUAL(2);
  

    private final int distanciaAoLagar;

    private TipoAzeitona(int distanciaAoLagar){
        this.distanciaAoLagar = distanciaAoLagar;
    }

    public int getDistanciaAoLagar(){
        return distanciaAoLagar;
    }
}
