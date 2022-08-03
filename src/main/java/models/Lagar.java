package models;

import configuracoes.Configuracoes;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Lagar implements Runnable {

    private int quantidadeDeAzeitonasArmazenadasEmToneladas;
    private int capacidadeMinimaDaFila;
    private int capacidadeMaximaDaFila;
    private int capacidadeDeRecepcaoSimultanea;
    private boolean estaDisponivel = true;
    private ConcurrentLinkedQueue<Caminhao> filaDeCaminhao = new ConcurrentLinkedQueue<>();

    private boolean controle = true;

    public Lagar(int capacidadeMinimaDaFila, int capacidadeMaximaDaFila, int capacidadeDeRecepcaoSimultanea) {
        this.quantidadeDeAzeitonasArmazenadasEmToneladas = 0;
        this.capacidadeMaximaDaFila = capacidadeMaximaDaFila;
        this.capacidadeMinimaDaFila = capacidadeMinimaDaFila;
        this.capacidadeDeRecepcaoSimultanea = capacidadeDeRecepcaoSimultanea;
        this.estaDisponivel = true;
    }

    public boolean isDisponivel() {
        return estaDisponivel;
    }

    public synchronized void receberCaminhao(Caminhao caminhao) {

        if (filaDeCaminhao.size() >= 3 && controle) {
            this.run();
        }

        if (filaDeCaminhao.size() >= capacidadeMaximaDaFila) {
            estaDisponivel = false;
        } else {
            filaDeCaminhao.add(caminhao);
        }
    }

    public void descarregarCaminhao(String areaDeDescarregamento) {
        if (filaDeCaminhao.size() > 0) {
            try {
                Caminhao caminhao = filaDeCaminhao.remove();
                Thread.sleep((caminhao.getCapacidadeMaximaDeTransporte() / Configuracoes.getToneladasPorSegundo()) * 1000);
                quantidadeDeAzeitonasArmazenadasEmToneladas += caminhao.getCapacidadeMaximaDeTransporte();
                registrarTrabalhoRealizado(caminhao, areaDeDescarregamento);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }

        if (filaDeCaminhao.size() <= Configuracoes.getCapacidadeMinimaDaFila()) {
            estaDisponivel = true;
        }
    }

    public void registrarTrabalhoRealizado(Caminhao caminhao, String areaDeDescarregamento) {
        LocalTime hora = LocalTime.now();
        var formatador = DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println(hora.format(formatador) + " "
                + String.format("%4s", quantidadeDeAzeitonasArmazenadasEmToneladas + "").replace(" ", "0") + " >> "
                + caminhao.getCapacidadeMaximaDeTransporte() + " toneladas de " + caminhao.getPlantacao().getTipoAzeitona()
                + " na " + areaDeDescarregamento
                + " de origem da " + caminhao.getPlantacao().getNome()
                + " com tempo total de " + Duration.between(caminhao.getMomentoCriacao(), Instant.now()).toSeconds() + " segundos");
    }

    @Override
    public void run() {

        controle = false;

        for (int i = 1; i <= capacidadeDeRecepcaoSimultanea; i++) {
            String areaDeDescarregamento = String.format("recepcao %s", i);
            new Thread(() -> {

                while (filaDeCaminhao.size() > 0) {
                    descarregarCaminhao(areaDeDescarregamento);
                }
            }, areaDeDescarregamento).start();
        }
    }
}