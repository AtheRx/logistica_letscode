import java.util.ArrayList;
import java.util.List;

public final class ManipulaArquivo {
    Fazenda fazenda;
    List<Plantacao> listaPlantacao = new ArrayList<>();
    List<Silo> listaSilo = new ArrayList<>();
    Lagar lagar;
    List<String> infoCaminhao; // mapear as informações que o caminhao precisa. pode ser um map?
    DiaTransporte diaTransporte;

    public ManipulaArquivo(String regras) {
        // abre o arquivo e faz as leituras e cria os mapeamentos
        // definir se fazenda e lagar serão static na definicao. lembrar conceito
        // mapeamentos
        // new data
        // new plantacao
        // new fazenda (obrigatorio criar plantacao antes de fazenda)
        // new silo
        // new lagar (obrigatorio criar silo antes de lagar)
        // new info caminhao

        // tempoCarga = tonelada * vazaodeCarga
        Plantacao plantacao = new Plantacao("Azeitona1", 4, 8);
        listaPlantacao.add(plantacao);
        fazenda = new Fazenda(listaPlantacao);
        Silo silo = new Silo("Silo1");
        listaSilo.add(silo);
        lagar = new Lagar(listaSilo, 8);
        //
        //
    }

    // metodo retorna object pq precisarmos das infos geradas no arquivo para
    // execucao
    public Object mapeiaClasses() {
        return this;
    }

    public static void geraRelatorio(Caminhao caminhao) {
        // geracao de escrita do relatorio deve ser synchronized
        // pq cada caminhão antes de morrer deve conseguir escrever no arquivo
        // e dois caminhoes podem tentar escrever simultaneamente ao sair do lagar

    }
}
