public class Caminhao extends Thread{
    private int capacidadeEmT;
    private int fatorMultDaCarga;
    private double fatorMultDaDescarga;

    public Caminhao(int capacidadeEmT, int fatorMultDaCarga){
        this.capacidadeEmT = capacidadeEmT;
        this.fatorMultDaCarga = fatorMultDaCarga;
        this.fatorMultDaDescarga = 1 / fatorMultDaCarga;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("Transportando");
    }
    // ::run
	// sleep(plantacao.getDistancia)
}
