import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Lagar deveria ser uma thread?? para notificar quando o caminhão pode começar a descarregar
//ou o silo deveria ser uma thread que roda quando seu estado não é ocupado? (opção melhor)
//silo pode ser um callable que quando libera sua ocupação, manda pro caminhao o notify para
//liberacao da thread e o nome do silo que foi liberado
//toda vez que um silo estiver desocupado, ele faz um notify na thread do caminhão que ta na 
//primeira posicao da fila. ja que o caminhao estava em wait
//silo pode ser uma thread? pq ela vai morrer em algum momento apos sua execução. vai ficar 
//viva ate terminar os dois minutos de execucao e ate terminar a fila ai sim pode terminar a 
//execucao da thread main

public final class Lagar {
    private Integer tempoDescarga;
    private List<Silo> silos;
    private Queue<Caminhao> filaEsperaLagar = new LinkedList<>();

    public Lagar(List<Silo> silos, Integer tempoDescarga) {
        this.silos = silos;
        this.tempoDescarga = tempoDescarga;
    }

    public List<Silo> getSilos() {
        return silos;
    }

    public Integer getTempoDescarga() {
        return tempoDescarga;
    }

    public Queue<Caminhao> getFilaEsperaLagar() {
        return filaEsperaLagar;
    }

    public void adicionaCaminhaoFila(Caminhao caminhao) {
        filaEsperaLagar.add(caminhao);
    }

}
