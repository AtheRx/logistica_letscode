public class Plantacao {
    private String nomeVariedadeAzeitona;
    private Integer tempoAteLagar;
    private Boolean isOcupado;
    private Boolean travaProducao;
    private Integer tempoCarga;

    public Plantacao(String nomeVariedadeAzeitona, Integer tempoAteLagar, Integer tempoCarga) {
        this.nomeVariedadeAzeitona = nomeVariedadeAzeitona;
        this.tempoAteLagar = tempoAteLagar;
        this.isOcupado = false;
        this.tempoCarga = tempoCarga;
    }

    public void setNomeVariedadeAzeitona(String nomeVariedadeAzeitona) {
        this.nomeVariedadeAzeitona = nomeVariedadeAzeitona;
    }

    public void setTempoAteLagar(Integer tempoAteLagar) {
        this.tempoAteLagar = tempoAteLagar;
    }

    public String getNomeVariedadeAzeitona() {
        return nomeVariedadeAzeitona;
    }

    public Integer getTempoAteLagar() {
        return tempoAteLagar;
    }

    public void setIsOcupado(Boolean isOcupado) {
        this.isOcupado = isOcupado;
    }

    public Boolean getIsOcupado() {
        return isOcupado;
    }

    public void setTempoCarga(Integer tempoCarga) {
        this.tempoCarga = tempoCarga;
    }

    public Integer getTempoCarga() {
        return tempoCarga;
    }

    public void setTravaProducao(Boolean travaProducao) {
        this.travaProducao = travaProducao;
    }

    public Boolean getTravaProducao() {
        return travaProducao;
    }

}
