package models;

import configuracoes.Configuracoes;
import factorys.FabricaDeCaminhoes;

import java.time.Duration;
import java.time.Instant;

public class Plantacao implements Runnable{
    private String tipoAzeitona;
    private int distanciaParaOLagarEmSegundos;
    private Lagar lagar;

    private String nome;

    public Plantacao(String nome, String tipoAzeitona, int distanciaParaOLagar, Lagar lagar) {
        this.nome = nome;
        this.tipoAzeitona = tipoAzeitona;
        this.distanciaParaOLagarEmSegundos = distanciaParaOLagar;
        this.lagar = lagar;
    }

    public String getTipoAzeitona() {
        return tipoAzeitona;
    }

    public int getDistanciaParaOLagar() {
        return distanciaParaOLagarEmSegundos;
    }

    public void setDistanciaParaOLagar(int distanciaParaOLagar) {
        this.distanciaParaOLagarEmSegundos = distanciaParaOLagar;
    }

    public Lagar getLagar() {
        return lagar;
    }

    public void setLagar(Lagar lagar) {
        this.lagar = lagar;
    }

    public String getNome() {
        return nome;
    }

    private void carregarCaminhao(Caminhao caminhao){
        try {
            Thread.sleep((caminhao.getCapacidadeMaximaDeTransporte() / Configuracoes.getToneladasPorSegundo() ) * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void enviarCaminhao(Caminhao caminhao){

        try {
            Thread.sleep(getDistanciaParaOLagar()*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        lagar.receberCaminhao(caminhao);
    }


    @Override
    public void run() {

        Instant inicioDaExecucao = Instant.now();

        new Thread(()->{
            while (Duration.between(inicioDaExecucao, Instant.now()).toMinutes() < 2){
                if (lagar.isDisponivel()){
                    Caminhao caminhao = FabricaDeCaminhoes.criarCaminhao(this);
                    carregarCaminhao(caminhao);
                    enviarCaminhao(caminhao);
                }
            }
                        
        }, this.tipoAzeitona).start();

    }
}
