public class App {
    public static void main(String[] args) throws Exception {
        Configuracao configuracao = new Configuracao();

        Caminhao caminhao01 = new Caminhao(16, 2, "1");
        Caminhao caminhao02 = new Caminhao(16, 2, "2");
        Plantacao plantacao01 = new Plantacao(4, 2);
        Plantacao plantacao02 = new Plantacao(4, 2);



        //Objetivo.
        //Duas plantações executando ao mesmo tempo
        //Sugestão 1:
        //Criar dois runnables

        plantacao01.carregaCaminhao(caminhao01);
        plantacao02.carregaCaminhao(caminhao02);


        System.out.println("Thread main");




        
        
    }

}
