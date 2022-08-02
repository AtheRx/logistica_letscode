public final class Execucao extends Thread {

    // não sei se a thread execução herda threads. acho que não precisa
    public static void iniciar() {
        ManipulaArquivo arquivo = new ManipulaArquivo("regras.txt");
        arquivo.mapeiaClasses();
        long tempoInicioTarefa = System.currentTimeMillis();

        while ((System.currentTimeMillis() - tempoInicioTarefa) < 120_000) {
            // realizar stream p/ encontrar fazendas não ocupadas vendo se a trava produção
            // é false e p/ kda plantacao nao ocupada
            // inserir um lambda para criar um caminhão (thread já iniciada no construtor)
            // verificar se a travaProducao estiver true. Deve-se matar os caminhoes da
            // plantacao?
            // acho melhor ignorar esse problema. deixa ele terminar de carregar.
            // setar a plantacao como ocupada
            // abaixo inicio do molde do stream
            if (arquivo.fazenda.getPlantacoes().stream().filter(j -> !j.getIsOcupado()).findFirst().isPresent()) {
                Caminhao caminhao = new Caminhao(4, 2, arquivo.lagar, arquivo.fazenda.getPlantacoes().get(0));
                arquivo.fazenda.getPlantacoes().get(0).setIsOcupado(true);
            } // ...
            arquivo.lagar.getFilaEsperaLagar().element(); // olha quem eh o primeiro da fila
            // verificar fila dos lagares para saber se tem caminhao
            // e da pra fazer um stream para encontrar os silos vazios
            // setar silo como is ocupado (execucao ou caminhão que executara? atualmente
            // a lógica está duplicada). ver questão de silo ser thread

            // informar ao caminhao qual o silo. (tem como em tempo de execução?) precisa
            // pro relatorio
            // no fim do ciclo de vida do caminhao eu volto para essa execução? (acho que
            // não porque fecha o ciclo)
            //
            // aqui iremos verificar o tamanho da fila do lagar para saber se é hora de
            // suspender as plantações
            // Quando isso ocorrer colocar todas produções em modo travado true. setando
            // todos na lista de plantacoes

            // quando a fila estiver em 4 caminhoes, voltar o modo travado para false

        }
        // fechar plantacoes (isOcupado & travaProducao = true para todas) - pode fazer
        // com stream ou foreach
        // esperar acabar as threads dos caminhoes. precisa verificar? acho que sim.
        // verificar também os silos que precisam ser encerrados nesse momento

    }

    @Override
    public void run() {

    }
}
