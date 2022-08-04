import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Configuracao {
    private LocalDateTime data = LocalDateTime.now();
    private int qtdVariedade = 3;
    private int qtdPlantacoes = 5;
    // private Map<String,Integer> varidadeEQtd
// private Map<String,String> variedadeEDist
    private int qtdDeRecepcao = 3;
    // private List<Integer> capacidadeTransCaminhao
    private List<Integer> capacidadeDeCargaMinMax = List.of(4, 16);
    private List<Integer> capacidadeDeDescargaMinMax = List.of(4, 16);
    private int fatorMultiplicadorTPorS = 2;
    private int limiteSupFilaEsperaNoLagar = 12;
    private int limiteInfFilaParaVoltarAOperar = 4;
    private int limiteTempoDeInterrupcaoDaCargaMin = 2;

    public LocalDateTime getData() {
        return data;
    }

    public int getQtdVariedade() {
        return qtdVariedade;
    }

    public int getQtdPlantacoes() {
        return qtdPlantacoes;
    }

    public int getQtdDeRecepcao() {
        return qtdDeRecepcao;
    }

    public List<Integer> getCapacidadeDeCargaMinMax() {
        return capacidadeDeCargaMinMax;
    }

    public List<Integer> getCapacidadeDeDescargaMinMax() {
        return capacidadeDeDescargaMinMax;
    }

    public int getFatorMultiplicadorTPorS() {
        return fatorMultiplicadorTPorS;
    }

    public int getLimiteSupFilaEsperaNoLagar() {
        return limiteSupFilaEsperaNoLagar;
    }

    public int getLimiteInfFilaParaVoltarAOperar() {
        return limiteInfFilaParaVoltarAOperar;
    }

    public int getLimiteTempoDeInterrupcaoDaCargaMin() {
        return limiteTempoDeInterrupcaoDaCargaMin;
    }


}
