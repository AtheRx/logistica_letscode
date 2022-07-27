import java.util.List;

public final class ManipulaArquivo {
    Fazenda fazenda;
    Lagar lagar;
    List<String> infoCaminhao; // mapear as informações que o caminhao precisa. pode ser um map?

    public ManipulaArquivo(String regras) {
        // abre o arquivo e faz as leituras e chama os mapeamentos ou não. precisa
        // definir isso
    }

    // metodo retorna object em razao de precisarmos das infos do lagar e da fazenda
    // criados
    public Object mapeiaClasses() {
        return this;
    }

    public static void geraRelatorio(Caminhao caminhao) {
        // geracao de escrita do relatorio deve ser synchronized

    }
}
