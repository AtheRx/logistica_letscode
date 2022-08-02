package models;

import configuracoes.Configuracoes;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class Lagar implements Runnable {

    private int quantidadeDeAzeitonasArmazenadasEmToneladas;
    private int capacidadeMinimaDaFila;
    private int capacidadeMaximaDaFila;
    private int capacidadeDeRecepcaoSimultanea;
    private boolean estaDisponivel;
    private boolean plantacoesProduzindo;

    private CopyOnWriteArraySet<Plantacao> relacaoDePlantacoes = new CopyOnWriteArraySet<>();
    private ConcurrentLinkedQueue<Caminhao> filaDeCaminhao = new ConcurrentLinkedQueue<>();

    public Lagar(int capacidadeMinimaDaFila, int capacidadeMaximaDaFila, int capacidadeDeRecepcaoSimultanea) {
        this.quantidadeDeAzeitonasArmazenadasEmToneladas = 0;
        this.capacidadeMaximaDaFila = capacidadeMaximaDaFila;
        this.capacidadeMinimaDaFila = capacidadeMinimaDaFila;
        this.capacidadeDeRecepcaoSimultanea = capacidadeDeRecepcaoSimultanea;
        this.estaDisponivel = true;
        this.plantacoesProduzindo = true;
    }

    public boolean isDisponivel() {
        return estaDisponivel;
    }

    public synchronized void receberCaminhao(Caminhao caminhao) {

        filaDeCaminhao.add(caminhao);
        atualizarRelacaoDePlantacoes(caminhao.getPlantacao());

        if (filaDeCaminhao.size() >= capacidadeMaximaDaFila) {
            estaDisponivel = false;
        }
    }

    public void descarregarCaminhao(String areaDeDescarregamento) {
        if (!filaDeCaminhao.isEmpty()){
            try{
                Caminhao caminhao = filaDeCaminhao.remove();
                Thread.sleep((caminhao.getCapacidadeMaximaDeTransporte() / Configuracoes.getToneladasPorSegundo()) * 1000);
                quantidadeDeAzeitonasArmazenadasEmToneladas += caminhao.getCapacidadeMaximaDeTransporte();
                registrarTrabalhoRealizado(caminhao, areaDeDescarregamento);
            } catch (InterruptedException e){
                throw new RuntimeException();
            } catch (NoSuchElementException e){
                pausarOperacao(1000);
            }
        }

        if (filaDeCaminhao.size() <= 4) {
            estaDisponivel = true;
        }
    }

    public void registrarTrabalhoRealizado(Caminhao caminhao, String areaDeDescarregamento){
        LocalTime hora = LocalTime.now();
        var formatador = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println(hora.format(formatador) + " "
                + String.format("%4s", quantidadeDeAzeitonasArmazenadasEmToneladas +"").replace(" ", "0")  + " >> "
                + caminhao.getCapacidadeMaximaDeTransporte() + " toneladas de " + caminhao.getPlantacao().getTipoAzeitona()
                + " na " + areaDeDescarregamento
                + " de origem da " + caminhao.getPlantacao().getNome()
                + " com tempo total de " + Duration.between(caminhao.getMomentoCriacao(), Instant.now()).toSeconds() + " segundos");
    }

    public void atualizarRelacaoDePlantacoes(Plantacao plantacao){
        new Thread(() -> {
            if(!relacaoDePlantacoes.contains(plantacao)) {
                relacaoDePlantacoes.add(plantacao);
            }
        }, "atualizacaoDePlantacoes").start();
    }

    public void verificarestadoDasPlantacoes(){
        if (relacaoDePlantacoes.size() > 0){
            plantacoesProduzindo = relacaoDePlantacoes.stream()
                    .allMatch((plantacao) -> plantacao.isEmProducao() == true);
        }

        pausarOperacao(1000);

    }
    public void pausarOperacao(int tempoDeProcessamentoEmMilissegundos){
        try{
            Thread.sleep(tempoDeProcessamentoEmMilissegundos);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {

        for (int i = 1; i <= capacidadeDeRecepcaoSimultanea; i++){
            String areaDeDescarregamento = String.format("recepcao %s", i);
            new Thread(() -> {
                while (plantacoesProduzindo || !filaDeCaminhao.isEmpty()) {
                   descarregarCaminhao(areaDeDescarregamento);
                }
            }, areaDeDescarregamento).start();
        }

        new Thread(() -> {
            while(plantacoesProduzindo || !filaDeCaminhao.isEmpty()) {
                if (filaDeCaminhao.isEmpty()) {
                    verificarestadoDasPlantacoes();
                }
            }
        }, "VerificadorDePlantacao").start();

    }
}