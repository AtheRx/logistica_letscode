public final class Execucao extends Thread {

    public static void iniciar() {
        long tempoInicioTarefa = System.currentTimeMillis();
        ManipulaArquivo arquivo = new ManipulaArquivo("regras.txt");
        arquivo.mapeiaClasses();

        while ((System.currentTimeMillis() - tempoInicioTarefa) < 120_000) {
            // realizar stream p/ encontrar fazendas não ocupadas e p/ kda plantacao nao
            // ocupada
            // inserir um lambda para criar um caminhão (thread já iniciada no construtor)
            // setar a plantacao como ocupada
            arquivo.fazenda.getPlantacoes().stream(); // ...
            // verificar fila dos lagares para saber se tem caminhao
            // e da pra fazer um stream para encontrar os silos vazios
            // setar silo como is ocupado (execucao ou caminhão que executara? atualmente
            // a lógica está duplicada)
            // informar ao caminhao qual o silo. (tem como em tempo de execução?) precisa
            // pro relatorio
            // no fim do ciclo de vida do caminhao eu volto para essa execução? (acho que
            // não porque fecha o ciclo)
            //

        }
        // fechar plantacoes (isOcupado = true para todas) - pode fazer com stream ou
        // foreach
        // esperar acabar as threads dos caminhoes. precisa verificar?

    }

    @Override
    public void run() {

    }
}
