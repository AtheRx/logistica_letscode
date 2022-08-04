public class TarefaDescarrega implements Runnable {
    private Caminhao caminhao;


    public TarefaDescarrega(Caminhao caminhao) {
        this.caminhao = caminhao;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(this.caminhao.getTempoCarregamentoMili());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
