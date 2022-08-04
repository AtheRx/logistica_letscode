import java.util.List;

public final class Fazenda {
    private List<Plantacao> plantacoes;

    public Fazenda(List<Plantacao> plantacoes) {
        this.plantacoes = plantacoes;
    }

    public void imprimePlantacoes() {
        System.out.println(plantacoes.size() + "Plantações");
        for (Plantacao cultivo : plantacoes) {
            System.out.println("1 Plantação de " + cultivo.getNomeVariedadeAzeitona());
        }
    }

    public List<Plantacao> getPlantacoes() {
        return plantacoes;
    }
}
