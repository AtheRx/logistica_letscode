public class Caminhao{
    private String id;
    private int capacidadeEmT;
    private int fatorMultDaCarga;
    private double fatorMultDaDescarga;

    public Caminhao(int capacidadeEmT, int fatorMultDaCarga, String id){
        this.id = id;
        this.capacidadeEmT = capacidadeEmT;
        this.fatorMultDaCarga = fatorMultDaCarga;
        this.fatorMultDaDescarga = 1 / fatorMultDaCarga;
    }

    public String getId() {
        return id;
    }

}
