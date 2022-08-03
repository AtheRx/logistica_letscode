import configuracoes.Configuracoes;
import models.Fazenda;
import models.Lagar;
import models.Plantacao;

public class Main {
    public static void main(String[] args) {
        Configuracoes.carregar();

        Lagar lagar = new Lagar(Configuracoes.getCapacidadeMinimaDaFila(), Configuracoes.getCapacidadeMaximaDaFila(), Configuracoes.getCapacidadeDeRecepcaoSimultanea());

        Fazenda fazenda = new Fazenda(lagar);
        fazenda.criarPlantacao();

        for (Plantacao plantacao : fazenda.getListaplantacoes()) {
            plantacao.run();
        }

    }
}
