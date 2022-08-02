public class App {
    public static void main(String[] args) throws Exception {
        Leitor leitor = new Leitor();
        Fazenda fazenda = new Fazenda(leitor);
        fazenda.criarPlantacao();
        Plantacao plantacao = new Plantacao(leitor);
        plantacao.iniciar(fazenda);
      
    }
}


