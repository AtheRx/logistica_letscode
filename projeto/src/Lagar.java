import java.util.LinkedList;
import java.util.Queue;

public class Lagar extends Fazenda {
    private Leitor leitor;

    public Lagar(Leitor leitor) {
        super(leitor);
        this.leitor = leitor;
    }

    Queue<Caminhao> fila = new LinkedList<>();


    public synchronized void adicionarCaminhaoNaFila(Caminhao caminhao) {
        if (fila.size() < 12) {
            fila.add(caminhao);
        } else {
            //Parar a produção das plantações
            // this.setAtiva(false);
        }
        descarregaCaminhao(fila.poll());
    }

    public void descarregaCaminhao(Caminhao caminhao) {

        new Thread(caminhao.getTipoCarga()) {

            @Override
            public void run() {
                System.out.println("Descarregando caminhão " + caminhao.getTipoCarga());
                int tempoDeDescarga = (caminhao.getCarga() * leitor.getFatorMultiplicador().get(0)) / 4;
                caminhao.setContador(caminhao.getContador() + tempoDeDescarga);
                try {
                    Thread.sleep(caminhao.getTempoDescarga() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Relatorio relatorio = new Relatorio();
                relatorio.gerar(caminhao);
            }

        }.start();


    }


}
