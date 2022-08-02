package models;

import configuracoes.Configuracoes;
import factorys.FabricaDeCaminhoes;

import java.time.Duration;
import java.time.Instant;

public class Plantacao implements Runnable{
    private String tipoAzeitona;
    private int distanciaParaOLagarEmSegundos;
    private Lagar lagar;

    private boolean produzindo;

    public Plantacao(String tipoAzeitona, int distanciaParaOLagar, Lagar lagar) {
        this.tipoAzeitona = tipoAzeitona;
        this.distanciaParaOLagarEmSegundos = distanciaParaOLagar;
        this.lagar = lagar;
        this.produzindo = true;
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

    private void carregarCaminhao(Caminhao caminhao){
        try {
            Thread.sleep((caminhao.getCapacidadeMaximaDeTransporte() / Configuracoes.getToneladasPorSegundo() ) * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void enviarCaminhao(Caminhao caminhao){

        //System.out.println("Enviando caminhao " + tipoAzeitona);

        try {
            Thread.sleep(getDistanciaParaOLagar()*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        boolean largarDisponivel = lagar.receberCaminhao(caminhao);


        

    }

    // execução 2 minutos
    @Override
    public void run() {

        Instant inicioDaExecucao = Instant.now();

        new Thread(()->{
            
            while (Duration.between(inicioDaExecucao, Instant.now()).toMinutes() < 2){ // corrigir para 2 minutos depois
                if (lagar.isEstaDisponivel()){
                    Caminhao caminhao = FabricaDeCaminhoes.criarCaminhao();
                    carregarCaminhao(caminhao);
                    enviarCaminhao(caminhao);
                }
            }
        }, this.tipoAzeitona).start();

    }
}
