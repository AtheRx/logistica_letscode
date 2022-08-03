package factorys;

import configuracoes.Configuracoes;
import models.Lagar;
import models.Plantacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FabricaDePlantacoes {
    private List<Plantacao> listaplantacoes = new ArrayList<>();
    private Lagar lagar;


    public FabricaDePlantacoes(Lagar lagar) {
        this.lagar = lagar;
    }

    public List<Plantacao> getListaplantacoes() {
        return listaplantacoes;
    }

    public void criarPlantacao() {
        for (Map.Entry<String, Integer> item : Configuracoes.getVariedadeEQuantidade().entrySet()) {
            adicionarPlantacao(item.getKey(), item.getValue());
            adicionarDistancia();
        }

    }

    public void adicionarPlantacao(String tipoAzeitona, int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            Plantacao plantacao = new Plantacao.PlatancaoBuilder().nome("plantacao " + i )
                    .tipoAzeitona(tipoAzeitona)
                    .distanciaParaOLagarEmSegundos(0)
                    .lagar(lagar)
                    .build();

            listaplantacoes.add(plantacao);
        }


    }

    public void adicionarDistancia() {
        int numeroPlantacao = 1;
        for (Plantacao plantacao : listaplantacoes) {
            plantacao.setNome("Plantacao " + numeroPlantacao);
            numeroPlantacao++;
            for (Map.Entry<String, Integer> item : Configuracoes.getVariedadeEDistancia().entrySet()) {
                if (plantacao.getTipoAzeitona().equals(item.getKey())) {
                    plantacao.setDistanciaParaOLagar(item.getValue());
                }
            }

        }
    }

}