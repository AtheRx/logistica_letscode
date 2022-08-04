public class TarefaTransporte implements Runnable {
    private long distanciaAoLagar;
    private Caminhao caminhao;
    private Lagar lagar;


    public TarefaTransporte(Caminhao caminhao, Lagar lagar, long distanciaAoLagar) {
        this.caminhao = caminhao;
        this.lagar = lagar;
        this.distanciaAoLagar = distanciaAoLagar;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(this.distanciaAoLagar * 1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
