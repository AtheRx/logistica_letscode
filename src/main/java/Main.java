import configuracoes.Configuracoes;
import factorys.FabricaDePlantacoes;
import models.Lagar;
import models.Plantacao;
import models.Relatorio;

public class Main {
    public static void main(String[] args) {
        Configuracoes.carregar();

        System.out.println(Configuracoes.getCapacidadeDeRecepcaoSimultanea());

        Lagar lagar = new Lagar.LagarBuild()
                .capacidadeMinimaDaFila(Configuracoes.getCapacidadeMinimaDaFila())
                .capacidadeMaximaDaFila(Configuracoes.getCapacidadeMaximaDaFila())
                .capacidadeDeRecepcaoSimultanea(Configuracoes.getCapacidadeDeRecepcaoSimultanea())
                .relatorio(new Relatorio(Configuracoes.nomeRelatorio())) // Corrigir para colocar o ano que vem do arquivo de regras no titulo do relatorio
                .build();


        FabricaDePlantacoes fazenda = new FabricaDePlantacoes(lagar);
        fazenda.criarPlantacao();

        for (Plantacao plantacao : fazenda.getListaplantacoes()) {
            plantacao.run();
        }

        lagar.run();

    }
}
