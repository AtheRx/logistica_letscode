package main;

import main.models.Plantacao;

public class TarefaEntregarAzeitonaNoLagar implements Runnable {
    private Plantacao plantacao;

    public TarefaEntregarAzeitonaNoLagar(Plantacao plantacao){
        this.plantacao = plantacao;
    }

    @Override
    public void run() {
       synchronized (this){
           System.out.println("estou levando as azeitonas espero que o lagar me receba " 
            + Thread.currentThread().getName());

           //            new Thread(new TarefaCaminhaoCarregadoDeAzeitonas(), "Caminhao de Azeitonas").start();;
           this.notifyAll();
        }
        

       }

}
