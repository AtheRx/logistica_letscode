package models;

import java.time.Instant;

public class Caminhao {
    private int capacidadeMaximaDeTransporte;
    private Instant momentoCriacao;
    private Plantacao plantacao;


    public Caminhao(int capacidadeDeTransporte, Plantacao plantacao) {

        this.capacidadeMaximaDeTransporte = capacidadeDeTransporte;
        this.plantacao = plantacao;
        this.momentoCriacao = Instant.now();
    }

    public int getCapacidadeMaximaDeTransporte() {
        return capacidadeMaximaDeTransporte;
    }

    public Instant getMomentoCriacao() {
        return momentoCriacao;
    }

    public Plantacao getPlantacao() {
        return plantacao;
    }

}
