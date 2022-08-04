import java.util.Map;

public class Plantacao extends Fazenda {

    private String tipoPlantacao;
    private boolean isOcupada = false;
    private Leitor leitor;
    private Map<String, Integer> variedadeEDist;
    private int distanciaDoLagar;

    public Plantacao(Leitor leitor) {
        super(leitor);
        this.leitor = leitor;
    }

    public String getTipoPlantacao() {
        return tipoPlantacao;
    }

    public void setTipoPlantacao(String tipoPlantacao) {
        this.tipoPlantacao = tipoPlantacao;
    }

    public boolean isOcupada() {
        return isOcupada;
    }

    public void setOcupada(boolean isOcupada) {
        this.isOcupada = isOcupada;
    }

    public Map<String, Integer> getVariedadeEDist() {
        return variedadeEDist;
    }

    public void setVariedadeEDist(Map<String, Integer> variedadeEDist) {
        this.variedadeEDist = variedadeEDist;
    }

    public int getDistanciaDoLagar() {
        return distanciaDoLagar;
    }

    public void setDistanciaDoLagar(int distanciaDoLagar) {
        this.distanciaDoLagar = distanciaDoLagar;
    }

    public void iniciar(Fazenda fazenda) {
        // while(fazenda.isAtiva()){
        //     fazenda.getListaPlantacoes().stream()
        //           .filter(plantacao -> plantacao.isOcupada == false)
        //           .forEach(plantacaoDesocupada -> plantacaoDesocupada.carregarCaminhao(new Caminhao(plantacaoDesocupada.getNome(), plantacaoDesocupada.leitor.getCapacidadeTransCaminhao())));

        // }
        int limiteInferiorCarga = leitor.getCapacidadeTransCaminhao().get(0);
        int limiteSuperiorCarga = leitor.getCapacidadeTransCaminhao().get(1);


        fazenda.getListaPlantacoes()
                .stream()
                .filter(plantacao -> plantacao.isOcupada == false)
                .forEach(plantacaoDesocupada -> {
                    int carga = GerarRandomico.randomico(limiteInferiorCarga, limiteSuperiorCarga);
                    Caminhao caminhao = new Caminhao.CaminhaoBuilder()
                            .carga(carga)
                            .tempocarga(carga / 2)
                            .tempodescarga(carga / 2)
                            .distanciadolagar(plantacaoDesocupada.getDistanciaDoLagar())
                            .tipoCarga(plantacaoDesocupada.getTipoPlantacao())
                            .build();
                    plantacaoDesocupada.carregarCaminhao(caminhao);
                });
    }

    public void carregarCaminhao(Caminhao caminhao) {
        //Ainda precisa ser verificada se a plantação está ocupada;

        new Thread(caminhao.getTipoCarga()) {

            @Override
            public void run() {
                System.out.println("Carregando caminhão " + caminhao.getTipoCarga());
                caminhao.setData(leitor.getData());
                int tempoDeCarga = (caminhao.getCarga() * leitor.getFatorMultiplicador().get(0)) / 4;
                caminhao.setContador(tempoDeCarga);
                try {
                    Thread.sleep(caminhao.getTempoCarga() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                transportarCaminhao(caminhao);

            }

        }.start();


    }

    public void transportarCaminhao(Caminhao caminhao) {

        new Thread(caminhao.getTipoCarga()) {

            @Override
            public void run() {
                System.out.println("Transportando caminhão " + caminhao.getTipoCarga());
                caminhao.setContador(caminhao.getContador() + (caminhao.getDistanciaDoLagar() * 1000));

                try {
                    Thread.sleep(caminhao.getDistanciaDoLagar() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Lagar lagar = new Lagar(leitor);
                lagar.adicionarCaminhaoNaFila(caminhao);

            }

        }.start();

    }


}
