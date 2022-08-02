import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TarefaCarrega implements Runnable {
    private Caminhao caminhao;
    private Plantacao plantacao;
    private Lagar lagar;
    
    private ExecutorService transportesPoll = Executors.newCachedThreadPool();

    public TarefaCarrega(Caminhao caminhao, Plantacao plantacao, Lagar lagar) {
        this.caminhao = caminhao;
        this.plantacao = plantacao;
        this.lagar = lagar;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(this.caminhao.getTempoCarregamentoMili());
            transportesPoll.execute(new TarefaTransporte(caminhao, lagar, plantacao.getDistanciaAoLagar()));
            
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
    }
    
}
