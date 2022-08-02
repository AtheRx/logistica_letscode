package factorys;

import models.Caminhao;

public class FabricaDeCaminhoes {
    public static Caminhao criarCaminhao(){
        return new Caminhao(6);
    }
}
