package models;

import configuracoes.Configuracoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fazenda {
    private List<Plantacao> listaplantacoes = new ArrayList<>();
    private Lagar lagar;


    public Fazenda(Lagar lagar) {
        this.lagar = lagar;
    }

    public List<Plantacao> getListaplantacoes() {
        return listaplantacoes;
    }

    public void criarPlantacao() {
        for (Map.Entry<String, Integer> item : Configuracoes.getVariedadeEQtd().entrySet()) {
            adicionarPlantacao(item.getKey(), item.getValue());
            adicionarDistancia();
        }

    }

    public void adicionarPlantacao(String tipoAzeitona, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            Plantacao plantacao = new Plantacao("Plantacao 1", tipoAzeitona, 0, lagar);
            listaplantacoes.add(plantacao);


        }


    }

    public void adicionarDistancia() {
        int numeroPlantacao = 1;
        for (Plantacao plantacao : listaplantacoes) {
            plantacao.setNome("Plantacao " + numeroPlantacao);
            numeroPlantacao++;
            for (Map.Entry<String, Integer> item : Configuracoes.getVariedadeEDist().entrySet()) {
                if (plantacao.getTipoAzeitona().equals(item.getKey())) {
                    plantacao.setDistanciaParaOLagar(item.getValue());
                }
            }

        }
    }

}

