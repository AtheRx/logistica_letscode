package factorys;

import configuracoes.Configuracoes;
import models.Caminhao;
import models.Plantacao;

import java.util.Random;

public class FabricaDeCaminhoes {
    public static Caminhao criarCaminhao(Plantacao plantacao){
        int cargaMaxima = Configuracoes.getCapacidadeDeTransporte().get(1);
        int cargaMinima = Configuracoes.getCapacidadeDeTransporte().get(0);

        Random random = new Random();
        int carga = random.nextInt((cargaMaxima - cargaMinima) + 1) + cargaMinima;;
        return new Caminhao(carga, plantacao);
    }
}
