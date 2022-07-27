
public class Silo {
    private String nomeSilo;
    private Boolean isOcupado;

    public Silo(String nomeSilo) {
        this.nomeSilo = nomeSilo;
        this.isOcupado = false;
    }

    public String getNomeSilo() {
        return nomeSilo;
    }

    public void setNomeSilo(String nomeSilo) {
        this.nomeSilo = nomeSilo;
    }

    public Boolean getIsOcupado() {
        return isOcupado;
    }

    public void setIsOcupado(Boolean isOcupado) {
        this.isOcupado = isOcupado;
    }

}
