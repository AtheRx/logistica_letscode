package models;

import configuracoes.Configuracoes;

import java.time.Duration;
import java.time.Instant;
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
    private Relatorio relatorio;

    private Lagar(LagarBuild build) {
        this.quantidadeDeAzeitonasArmazenadasEmToneladas = 0;
        this.plantacoesProduzindo = true;
        this.capacidadeMaximaDaFila = build.capacidadeMaximaDaFila;
        this.capacidadeMinimaDaFila = build.capacidadeMinimaDaFila;
        this.capacidadeDeRecepcaoSimultanea = build.capacidadeDeRecepcaoSimultanea;
        this.estaDisponivel = build.estaDisponivel;

        this.relatorio = build.relatorio;

    }

    public boolean isDisponivel() {
        return estaDisponivel;
    }

    public Relatorio getRelatorio() {
        return this.relatorio;
    }

    public synchronized void receberCaminhao(Caminhao caminhao) {

        if(estaDisponivel){
            filaDeCaminhao.add(caminhao);
            atualizarRelacaoDePlantacoes(caminhao.getPlantacao());
        }

        if (filaDeCaminhao.size() >= capacidadeMaximaDaFila) {
            estaDisponivel = false;
        } else if (filaDeCaminhao.size() <= capacidadeMinimaDaFila) {
            estaDisponivel = true;
        }

    }

    private void pausarOperacao(int tempoDeProcessamentoEmMilissegundos) {
        try {
            Thread.sleep(tempoDeProcessamentoEmMilissegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void carregarSilos(Caminhao caminhao){
        try {
            Thread.sleep((caminhao.getCapacidadeMaximaDeTransporte() / Configuracoes.getToneladasPorSegundo()) * 1000);
            quantidadeDeAzeitonasArmazenadasEmToneladas += caminhao.getCapacidadeMaximaDeTransporte();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    private synchronized void registrarTrabalhoRealizado(Caminhao caminhao, String areaDeDescarregamento) {
        LocalTime hora = LocalTime.now();
        var formatador = DateTimeFormatter.ofPattern("HH:mm:ss");
        String registro = hora.format(formatador) + " "
                + String.format("%4s", quantidadeDeAzeitonasArmazenadasEmToneladas + "")
                .replace(" ", "0") + " >> "
                + caminhao.getCapacidadeMaximaDeTransporte() + " toneladas de " + caminhao.getPlantacao().getTipoAzeitona()
                + " na " + areaDeDescarregamento
                + " de origem da " + caminhao.getPlantacao().getNome()
                + " com tempo total de " + Duration.between(caminhao.getMomentoCriacao(), Instant.now()).toSeconds()
                + " segundos" + System.lineSeparator();

        System.out.print(registro);
        this.relatorio.imprime(registro);

        try {
            if (Thread.activeCount() <= 2) {
                this.relatorio.fecharRegistro();
            }
        } catch (IllegalStateException e) {
            //TODO: handle exception
        }
    }

    public void descarregarCaminhao(String areaDeDescarregamento) {
        if (!filaDeCaminhao.isEmpty()) {
            try{
                Caminhao caminhao = filaDeCaminhao.remove();
                carregarSilos(caminhao);
                registrarTrabalhoRealizado(caminhao, areaDeDescarregamento);
            }catch (NoSuchElementException e) {
                pausarOperacao(1000);
            }
        }

        if (filaDeCaminhao.size() <= capacidadeMinimaDaFila) {
            estaDisponivel = true;
        }
    }

    private void atualizarRelacaoDePlantacoes(Plantacao plantacao) {
        new Thread(() -> {
            if (!relacaoDePlantacoes.contains(plantacao)) {
                relacaoDePlantacoes.add(plantacao);
            }
        }, "atualizacaoDePlantacoes").start();
    }

    private void verificarestadoDasPlantacoes() {
        if (relacaoDePlantacoes.size() > 0) {
            plantacoesProduzindo = relacaoDePlantacoes.stream()
                    .anyMatch((plantacao) -> plantacao.isEmProducao());
        }
        pausarOperacao(1000);
    }

    @Override
    public void run() {
        estaDisponivel = true;
        for (int i = 1; i <= capacidadeDeRecepcaoSimultanea; i++) {
            String areaDeDescarregamento = String.format("recepcao %s", i);
            new Thread(() -> {
                while (plantacoesProduzindo || !filaDeCaminhao.isEmpty()) {
                    descarregarCaminhao(areaDeDescarregamento);
                }
                System.out.println("No fim:  " + filaDeCaminhao.size());
            }, areaDeDescarregamento).start();

        }



        new Thread(() -> {
            while (plantacoesProduzindo || !filaDeCaminhao.isEmpty()) {
                if (filaDeCaminhao.isEmpty()) {
                    verificarestadoDasPlantacoes();
                }
            }
        }, "VerificadorDePlantacao").start();

    }

    public static class LagarBuild{
        private int capacidadeMinimaDaFila;
        private int capacidadeMaximaDaFila;
        private int capacidadeDeRecepcaoSimultanea;
        private boolean estaDisponivel;
        private boolean plantacoesProduzindo;
        private CopyOnWriteArraySet<Plantacao> relacaoDePlantacoes = new CopyOnWriteArraySet<>();
        private ConcurrentLinkedQueue<Caminhao> filaDeCaminhao = new ConcurrentLinkedQueue<>();
        private Relatorio relatorio;


        public LagarBuild capacidadeMinimaDaFila(int capacidadeMinimaDaFila) {
            this.capacidadeMinimaDaFila = capacidadeMinimaDaFila;
            return this;
        }

        public LagarBuild capacidadeMaximaDaFila(int capacidadeMaximaDaFila) {
            this.capacidadeMaximaDaFila = capacidadeMaximaDaFila;
            return this;
        }

        public LagarBuild capacidadeDeRecepcaoSimultanea(int capacidadeDeRecepcaoSimultanea) {
            this.capacidadeDeRecepcaoSimultanea = capacidadeDeRecepcaoSimultanea;
            return this;
        }

        public LagarBuild estaDisponivel(boolean estaDisponivel) {
            this.estaDisponivel = estaDisponivel;
            return this;
        }

        public LagarBuild plantacoesProduzindo(boolean plantacoesProduzindo) {
            this.plantacoesProduzindo = plantacoesProduzindo;
            return this;
        }

        public LagarBuild relacaoDePlantacoes(CopyOnWriteArraySet<Plantacao> relacaoDePlantacoes) {
            this.relacaoDePlantacoes = relacaoDePlantacoes;
            return this;
        }

        public LagarBuild filaDeCaminhao(ConcurrentLinkedQueue<Caminhao> filaDeCaminhao) {
            this.filaDeCaminhao = filaDeCaminhao;
            return this;
        }

        public LagarBuild relatorio(Relatorio relatorio) {
            this.relatorio = relatorio;
            return this;
        }

        public Lagar build(){
            Lagar lagar = new Lagar(this);
            return lagar;
        }

    }
}