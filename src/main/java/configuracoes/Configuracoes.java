package configuracoes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Configuracoes {

    private static LocalDate dataAtual;
    private static long toneladasPorSegundo;
    private static List<String> tiposAzeitona = new ArrayList<>();

    private Configuracoes() {
    }

    public static LocalDate getDataAtual() {
        return dataAtual;
    }

//    public static String getTipoAzeitona() {
//        return tiposAzeitona.equals();
//    }

    public static long getToneladasPorSegundo() {
        return toneladasPorSegundo;
    }

    public static void carregar() {
        toneladasPorSegundo = 2;
        tiposAzeitona.add("Galega");
        tiposAzeitona.add("Cordovil");
        tiposAzeitona.add("Picual");


    }
}
