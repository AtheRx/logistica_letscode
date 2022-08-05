import configuracoes.Configuracoes;
import factorys.FabricaDePlantacoes;
import models.Lagar;
import models.Plantacao;
import models.Relatorio;

public class Main {
    public static void main(String[] args) {

        boolean keepRunning = true;
        Relatorio relatorio = new Relatorio(Configuracoes.nomeRelatorio());
        Configuracoes.carregar();

        Lagar lagar = new Lagar.LagarBuild()
                .capacidadeMinimaDaFila(Configuracoes.getCapacidadeMinimaDaFila())
                .capacidadeMaximaDaFila(Configuracoes.getCapacidadeMaximaDaFila())
                .capacidadeDeRecepcaoSimultanea(Configuracoes.getCapacidadeDeRecepcaoSimultanea())
                .relatorio(relatorio) 
                .build();


        FabricaDePlantacoes fazenda = new FabricaDePlantacoes(lagar);
        fazenda.criarPlantacao();

        for (Plantacao plantacao : fazenda.getListaplantacoes()) {
            plantacao.run();
        }

        lagar.run();

//        Thread threadControla = new Thread(new ControlaFechamentoRelatorio(relatorio));
//        threadControla.setDaemon(true);
//        try{
//            threadControla.start();
//        }finally{
//            //System.out.println("Teste");
//        }

        while(keepRunning) {

            //System.out.println(Thread.currentThread());
            try {
                //System.out.println(Thread.activeCount());

                if (Thread.activeCount() <= 2) {
                    Thread.sleep(3_000);
                    relatorio.fecharRegistro();
                    keepRunning = false;


                }
            } catch (Exception e) {

                System.out.printf("O Arquivo já foi fechado.%n" + e.getStackTrace());
            }
        }


    }

//    static class ControlaFechamentoRelatorio implements Runnable{
//        Relatorio relatorio;
//        public ControlaFechamentoRelatorio(Relatorio relatorio){
//            this.relatorio = relatorio;
//        }
//        @Override
//        public void run() {
//            boolean keepRunning = true;
//            while(keepRunning){
//
//                System.out.println(Thread.currentThread());
//                try {
//                    //System.out.println(Thread.activeCount());
//
//                    if (Thread.activehoCount() <= 2) {
//                        Thread.sleep(2_000);
//                        relatorio.fecharRegistro();
//                        //keepRunning = false;
//
//
//
//                    }
//                } catch (Exception e) {
//
//                    System.out.printf("O Arquivo já foi fechado.%n" + e.getStackTrace());
//                }
//            }
//        }
//    }

}
