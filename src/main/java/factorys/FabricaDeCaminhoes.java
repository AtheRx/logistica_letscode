package factorys;

import models.Caminhao;
import models.Plantacao;

import java.util.Random;

public class FabricaDeCaminhoes {
    public static Caminhao criarCaminhao(Plantacao plantacao){

        Random random = new Random();

        int carga = random.nextInt(12) + 4;
        return new Caminhao(carga, plantacao);
    }
}
