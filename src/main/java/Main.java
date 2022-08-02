import configuracoes.Configuracoes;
import models.Lagar;
import models.Plantacao;

public class Main {
    public static void main(String[] args) {
        Configuracoes.carregar();

        Lagar lagar = new Lagar(4,12,3);

        

        Plantacao plantacao1 = new Plantacao("Galega", 4, lagar);
        Plantacao plantacao2 = new Plantacao("Cordovil", 3, lagar);
        Plantacao plantacao3 = new Plantacao("Purcil", 2, lagar);

        plantacao1.run();
        plantacao2.run();
        plantacao3.run();


        lagar.run();

    }
}
