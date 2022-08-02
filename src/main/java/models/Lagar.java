package models;

import configuracoes.Configuracoes;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Lagar implements Runnable {

    private int amazenamento;
    private int capacidadeMinimaDaFila;
    private int capacidadeMaximaDaFila;
    private int capacidadeDeRecepcaoSimultanea;
    private boolean estaDisponivel = true;
    private ConcurrentLinkedQueue<Caminhao> filaDeCaminhao = new ConcurrentLinkedQueue<>();
    //private BlockingQueue<Caminhao>  filaDeCaminhao = new ArrayBlockingQueue<>();

    private boolean controle = true;

    public Lagar(int capacidadeMinimaDaFila, int capacidadeMaximaDaFila, int capacidadeDeRecepcaoSimultanea) {
        this.amazenamento = 0;
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

    @Override
    public void run() {

        Instant inicioDaExecucao = Instant.now();
        controle = false;

        new Thread(() -> {

            while (filaDeCaminhao.size() > 0) {

                if (filaDeCaminhao.size() <= 4) {
                    estaDisponivel = true;
                }

                Caminhao caminhao = filaDeCaminhao.remove();

                try {
                    Thread.sleep((caminhao.getCapacidadeMaximaDeTransporte() / Configuracoes.getToneladasPorSegundo()) * 1000);
                    amazenamento+= caminhao.getCapacidadeMaximaDeTransporte();
                    LocalTime hora = LocalTime.now();
                    var formatador = DateTimeFormatter.ofPattern("HH:mm:ss");

                    System.out.println(hora.format(formatador) + " "
                            + String.format("%4s", amazenamento+"").replace(" ", "0")  + " >> "
                            + caminhao.getCapacidadeMaximaDeTransporte() + " toneladas de " + caminhao.getPlantacao().getTipoAzeitona()
                            + " na recepcao x"
                            + " de origem da " + caminhao.getPlantacao().getNome()
                            + " com tempo total de " + Duration.between(caminhao.getMomentoCriacao(), Instant.now()).toSeconds() + " segundos");
                    //System.out.println("Na fila: " + filaDeCaminhao.size());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "Lagar").start();
    }
}