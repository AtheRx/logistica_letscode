import configuracoes.Configuracoes;
import factorys.FabricaDePlantacoes;
import models.Lagar;
import models.Plantacao;
import models.Relatorio;

public class Main {
    public static void main(String[] args) {
        boolean keepRunning = true;
        Relatorio relatorio = new Relatorio(Configuracoes.nomeRelatorio());
        Configuracoes.carregar();

        System.out.println(Configuracoes.getCapacidadeDeRecepcaoSimultanea());

        Lagar lagar = new Lagar.LagarBuild()
                .capacidadeMinimaDaFila(Configuracoes.getCapacidadeMinimaDaFila())
                .capacidadeMaximaDaFila(Configuracoes.getCapacidadeMaximaDaFila())
                .capacidadeDeRecepcaoSimultanea(Configuracoes.getCapacidadeDeRecepcaoSimultanea())
                .relatorio(relatorio) 
                .build();


        FabricaDePlantacoes fazenda = new FabricaDePlantacoes(lagar);
        fazenda.criarPlantacao();

        for (Plantacao plantacao : fazenda.getListaplantacoes()) {
            plantacao.run();
        }

        lagar.run();
        while(keepRunning){

            try {
                if (Thread.activeCount() <= 1) {
                    relatorio.fecharRegistro();
                    keepRunning = false;

                }
            } catch (Exception e) {
                
                System.out.printf("O Arquivo já foi fechado.%n" + e.getStackTrace());
            } 
        }

    }
}
