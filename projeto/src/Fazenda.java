import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fazenda {
    private List<Plantacao> listaplantacoes = new ArrayList<>();
    private Leitor leitor;
    protected boolean ativa = true;


    public Fazenda(Leitor leitor) {
        this.leitor = leitor;
    }

    public List<Plantacao> getListaPlantacoes() {
        return listaplantacoes;
    }
    
    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public void criarPlantacao(){
        for (Map.Entry<String, Integer> item :leitor.getVariedadeEQtd().entrySet()) {
            adicionarPlantacao(item.getKey(), item.getValue());
            adicionarDistancia();  
        } 

    }

    public void adicionarPlantacao(String tipoPlantacao, int quantidade){
        for(int i = 0; i < quantidade; i++){
            Plantacao plantacao = new Plantacao(leitor);
            plantacao.setTipoPlantacao(tipoPlantacao);
            listaplantacoes.add(plantacao);

        }
        
    }

    public void adicionarDistancia(){
        for (Plantacao plantacao : listaplantacoes) {
            for (Map.Entry<String, Integer> item :leitor.getVariedadeEDist().entrySet()) {
                 if(plantacao.getTipoPlantacao().equals(item.getKey())){
                    plantacao.setDistanciaDoLagar(item.getValue());
                 }
            } 
            
        }
    }

}
