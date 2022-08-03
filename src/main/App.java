package main;

import main.models.Plantacao;

public class App {
    public static void main(String[] args) {
        Plantacao plantacao = new Plantacao();
        System.out.println("Hello World!");

        new Thread(new TarefaEntregarAzeitonaNoLagar(plantacao), "Thread Da Plantação").start();
        new Thread(new TarefaRecebendoAzeitonas(plantacao), "Thread Do Lagar").start();

        System.out.println("Sou a thread na main ----Eu imprimi depois das threads " + Thread.currentThread().getName());

    }
}
