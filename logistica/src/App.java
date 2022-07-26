public class App {
    public static void main(String[] args) throws Exception {
        Configuracao configuracao = new Configuracao();

        Caminhao caminhao01 = new Caminhao(16, 2);
        Plantacao plantacao01 = new Plantacao(4, 2);

        //Controle da plantação
        if( plantacao01.carregaCaminhao(caminhao01) ){
            plantacao01.start();
            //plantacao01.run();
            plantacao01.sleep(plantacao01.getTempoCarga() * 5000);
            
        }

        // Caminhao caminhao02 = new Caminhao(16, 2);
        // Plantacao plantacao02 = new Plantacao(4, 2);
        // if( plantacao02.carregaCaminhao(caminhao02) ){
        //     plantacao02.run();
        //     plantacao02.sleep(plantacao02.getTempoCarga() * 1000);
            
        // }

        System.out.print("Thread main");




        
        
    }

}
