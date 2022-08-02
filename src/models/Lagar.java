package models;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Lagar implements Runnable{
    private int capacidadeMinimaDaFila;
    private int capacidadeMaximaDaFila;
    private int capacidadeDeRecepcaoSimultanea;
    private boolean estaDisponivel = true;
    private ConcurrentLinkedQueue<Caminhao>  filaDeCaminhao = new ConcurrentLinkedQueue<>();
    //private BlockingQueue<Caminhao>  filaDeCaminhao = new ArrayBlockingQueue<>();

    public Lagar(int capacidadeMinimaDaFila, int capacidadeMaximaDaFila, int capacidadeDeRecepcaoSimultanea) {
        this.capacidadeMaximaDaFila = capacidadeMaximaDaFila;
        this.capacidadeMinimaDaFila = capacidadeMinimaDaFila;
        this.capacidadeDeRecepcaoSimultanea = capacidadeDeRecepcaoSimultanea;
        this.estaDisponivel = true;
    }

    public boolean isEstaDisponivel() {
        return estaDisponivel;
    }



//    public boolean adcionarCaminhaoNaFila(Caminhao caminhao){
//        if (filaDeCaminhao.size() < capacidadeMaximaDaFila){
//            filaDeCaminhao.add(caminhao);
//            return true;
//        }
//        if (filaDeCaminhao.size() == capacidadeMaximaDaFila){
//            estaDisponivel = false;
//            return false;
//        }
//
//    }

    public synchronized boolean receberCaminhao(Caminhao caminhao){

        
        //System.out.println(filaDeCaminhao.size());

        if(!estaDisponivel){
            System.out.println("Não diponivel");
            try {
                estaDisponivel = false;
               this.wait();
                //return false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (filaDeCaminhao.size() >= capacidadeMaximaDaFila){
            estaDisponivel = false;
            return false;
        }

        if (filaDeCaminhao.size() <= capacidadeMinimaDaFila){
            this.notifyAll();
            filaDeCaminhao.add(caminhao);
            System.out.println("Add " + Thread.currentThread() + " " + filaDeCaminhao.size());
            estaDisponivel = true;
            return true;
        }

        filaDeCaminhao.add(caminhao);
        System.out.println("Add " + Thread.currentThread() + " " + filaDeCaminhao.size());
        estaDisponivel = true;
        return true;
    }

    @Override
    public void run() {

        Instant inicioDaExecucao = Instant.now();
        
        var t1 = new Thread(()->{
            
            while (filaDeCaminhao.size()>0){ // corrigir para 2 minutos depois

                if(filaDeCaminhao.size()==4){
                    estaDisponivel = true;

                }
                if (filaDeCaminhao.size()>0){
                    filaDeCaminhao.remove();
                    System.out.println("Removeu " + filaDeCaminhao.size());
                    System.out.println(estaDisponivel);

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Lagar" );

        t1.setDaemon(true);
        t1.start();

    }
}
