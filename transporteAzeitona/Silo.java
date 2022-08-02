
public class Silo extends Thread {
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

    @Override
    public void run() {
        // ver como vai funcionar as regras dessa thread que precisa chamar o caminhao
        // se isOcupado = false
        // ver como bloquear ela visto que ela não pode morrer acho que o caminhao mesmo
        // pode fazer isso
    }

}
