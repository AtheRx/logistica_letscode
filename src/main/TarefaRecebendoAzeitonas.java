package main;

import main.models.Plantacao;

public class TarefaRecebendoAzeitonas implements Runnable {
    private Plantacao plantacao;

    public TarefaRecebendoAzeitonas(Plantacao plantacao ) {
        this.plantacao = plantacao;
    }
    @Override
    public void run() {
        synchronized (this){
            try {
                Thread.currentThread().sleep(4000);
                System.out.println("Eu sou o lagar estou recebendo boas azeitonas " + Thread.currentThread().getName());        
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
