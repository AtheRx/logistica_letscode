package models;

import configuracoes.Configuracoes;
import factorys.FabricaDeCaminhoes;

import java.time.Duration;
import java.time.Instant;

public class Plantacao implements Runnable {
    private String nome;
    private String tipoAzeitona;
    private int distanciaParaOLagarEmSegundos;
    private Lagar lagar;

    private boolean emProducao;

    private Plantacao(PlatancaoBuilder builder) {
        this.nome = builder.nome;
        this.tipoAzeitona = builder.tipoAzeitona;
        this.distanciaParaOLagarEmSegundos = builder.distanciaParaOLagarEmSegundos;
        this.lagar = builder.lagar;
        this.emProducao = builder.emProducao;
    }

    public int getDistanciaParaOLagarEmSegundos() {
        return distanciaParaOLagarEmSegundos;
    }

    public Lagar getLagar() {
        return lagar;
    }

    public void setLagar(Lagar lagar) {
        this.lagar = lagar;
    }

    public boolean isEmProducao() {
        return emProducao;
    }

    public String getTipoAzeitona() {
        return tipoAzeitona;
    }

    public int getDistanciaParaOLagar() {
        return distanciaParaOLagarEmSegundos;
    }


    public String getNome() {
        return nome;
    }

    private void carregarCaminhao(Caminhao caminhao) {
        try {
            Thread.sleep((caminhao.getCapacidadeMaximaDeTransporte() / Configuracoes.getToneladasPorSegundo()) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void enviarCaminhao(Caminhao caminhao) {

        try {
            Thread.sleep(getDistanciaParaOLagar() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lagar.receberCaminhao(caminhao);
    }


    @Override
    public void run() {

        Instant inicioDaExecucao = Instant.now();

        new Thread(() -> {
            while (Duration.between(inicioDaExecucao, Instant.now()).toMinutes() < 2) {
                if (lagar.isDisponivel()) {
                    Caminhao caminhao = FabricaDeCaminhoes.criarCaminhao(this);
                    carregarCaminhao(caminhao);
                    enviarCaminhao(caminhao);
                }
            }
            this.emProducao = false;

        }, this.tipoAzeitona).start();

    }

    public void setDistanciaParaOLagar(Integer distanciaParaOLagarEmSegundos) {
        this.distanciaParaOLagarEmSegundos = distanciaParaOLagarEmSegundos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static class PlatancaoBuilder{
        private String tipoAzeitona;
        private int distanciaParaOLagarEmSegundos;
        private Lagar lagar;
        private String nome;
        private boolean emProducao;

        public PlatancaoBuilder(){

        }

        public PlatancaoBuilder tipoAzeitona(String tipoAzeitona){
            this.tipoAzeitona = tipoAzeitona;
            return this;
        }

        public PlatancaoBuilder distanciaParaOLagarEmSegundos(int distanciaParaOLagarEmSegundos){
            this.distanciaParaOLagarEmSegundos = distanciaParaOLagarEmSegundos;
            return this;
        }
        public PlatancaoBuilder lagar(Lagar lagar){
            this.lagar = lagar;
            return this;
        }
        public PlatancaoBuilder nome(String nome){
            this.nome = nome;
            return this;
        }
        public PlatancaoBuilder emProducao(boolean emProducao){
            this.emProducao = emProducao;
            return this;
        }

        public Plantacao build(){
            Plantacao plantacao = new Plantacao(this);
            return plantacao;
        }

    }
}
