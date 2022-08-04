import java.util.concurrent.atomic.AtomicInteger;

public class Caminhao extends Thread {
    private Lagar lagar;
    private Plantacao plantacaoOrigem;
    private Integer capacidadeCaminhao;
    private Integer taxaCarregamento;
    private Integer siloDescarregado; // não lembro pra que essa varivavel
    // sera que recebe o indice do silo no qual ira descarregar?
    private AtomicInteger tempoTotalTransporte = new AtomicInteger(0);
    private Long momentoEntradaFila = (long) 0;

    public Caminhao(Integer capacidadeCaminhao, Integer taxaCarregamento, Lagar lagar, Plantacao plantacao) {
        this.plantacaoOrigem = plantacao;
        this.capacidadeCaminhao = capacidadeCaminhao;
        this.taxaCarregamento = taxaCarregamento;
        this.lagar = lagar;
        start();
    }

    public void setCapacidadeCaminhao(Integer capacidadeCaminhao) {
        this.capacidadeCaminhao = capacidadeCaminhao;
    }

    public Integer getCapacidadeCaminhao() {
        return capacidadeCaminhao;
    }

    public void setTaxaCarregamento(Integer taxaCarregamento) {
        this.taxaCarregamento = taxaCarregamento;
    }

    public Integer getTaxaCarregamento() {
        return taxaCarregamento;
    }

    public void setTempoTotalTransporte(AtomicInteger tempoTotalTransporte) {
        this.tempoTotalTransporte = tempoTotalTransporte;
    }

    public AtomicInteger getTempoTotalTransporte() {
        return tempoTotalTransporte;
    }

    public Plantacao getPlantacaoOrigem() {
        return plantacaoOrigem;
    }

    public void setPlantacaoOrigem(Plantacao plantacaoOrigem) {
        this.plantacaoOrigem = plantacaoOrigem;
    }

    // sera que variavel eh para determinar o silo que sera descarregado? gatilho da
    // execucao da thread de fila
    public void setSiloDescarga(Integer silo) {
        this.siloDescarregado = silo;
    }

    public Integer getSiloDescarregado() {
        return siloDescarregado;
    }

    public void setMomentoEntradaFila(Long tempoDeFila) {
        this.momentoEntradaFila = tempoDeFila;
    }

    public Long getMomentoEntradaFila() {
        return momentoEntradaFila;
    }

    @Override
    public void run() {
        try {
            System.out.println("Caminhão (nome thread) " + Thread.currentThread().getName() + " criado!");
            System.out.println("Carregando na plantacao " + plantacaoOrigem.getNomeVariedadeAzeitona());
            Thread.sleep(plantacaoOrigem.getTempoCarga());
            tempoTotalTransporte.addAndGet(plantacaoOrigem.getTempoCarga());
            plantacaoOrigem.setIsOcupado(false);
            System.out.println("Em transporte");
            Thread.sleep(plantacaoOrigem.getTempoAteLagar());
            tempoTotalTransporte.addAndGet(plantacaoOrigem.getTempoAteLagar());
            System.out.println("Chegou na Fila");
            lagar.adicionaCaminhaoFila(this);
            setMomentoEntradaFila(System.currentTimeMillis());
            Thread.currentThread().wait(); // não sei se eh assim que coloca ela pra esperar o notify
            // descobrir como contar o tempo de fila (OK) e suspender a thread para liberar
            // ela quando for sair da primeira posicao da fila.
            // ...
            // adicionar o tempo total transporte com esse tempo encontrado (OK)
            tempoTotalTransporte.addAndGet(Math.toIntExact(System.currentTimeMillis() - getMomentoEntradaFila()));
            // travar o silo com is ocupado antes de iniciar o descarregamento. a thread no
            // silo pode ser callable pq retorna qual o silo que o caminhao deve se dirigir
            // guardar a informacao de qual silo estou usando (uma opção + dificil)
            // outra opcao: essa thread caminhao run sera responsavel por dar a liberação do
            // silo para ele chamar outro caminhao da fila
            System.out.println("Descarregando no lagar");
            Thread.sleep(lagar.getTempoDescarga());
            // destravar o silo isOcupado false (fazer via bloqueio? pq a thread silo
            // morreria) fazer shutdown do silo no final dos dois minutos e esvaziamento da
            // fila
            tempoTotalTransporte.addAndGet(lagar.getTempoDescarga());
            ManipulaArquivo.geraRelatorio(this);
            // no caminhao estao todas as informacoes
            System.out.println("XXX Fim do caminhão XXX");
        } catch (InterruptedException e) {
            e.getStackTrace();
        }

    }

}