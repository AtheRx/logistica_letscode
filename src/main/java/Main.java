import configuracoes.Configuracoes;
import models.Lagar;
import models.Plantacao;

public class Main {
    public static void main(String[] args) {
        Configuracoes.carregar();

        Lagar lagar = new Lagar(4,12,3);

        Plantacao plantacao1 = new Plantacao("Plantacao 1","Galega", 4, lagar);
        Plantacao plantacao2 = new Plantacao("Plantacao 2","Galega", 4, lagar);
        Plantacao plantacao3 = new Plantacao("Plantacao 3","Cordovil", 3, lagar);
        Plantacao plantacao4 = new Plantacao("Plantacao 4","Cordovil", 3, lagar);
        Plantacao plantacao5 = new Plantacao("Plantacao 5","Purcil", 2, lagar);

        plantacao1.run();
        plantacao2.run();
        plantacao3.run();
        plantacao4.run();
        plantacao5.run();

    }
}
