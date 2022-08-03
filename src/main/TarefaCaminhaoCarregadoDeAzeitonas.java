package main;

import main.models.Caminhao;

public class TarefaCaminhaoCarregadoDeAzeitonas implements Runnable {
    private Integer Carga = 6;
    private String tipoDeAzeitona;
    

        public TarefaCaminhaoCarregadoDeAzeitonas(){
        }

        
    @Override
    public void run() {
        synchronized (this) {
            Caminhao caminhao = new Caminhao();
            System.out.println("Caminhão sendo carregado, capacidade X" + Thread.currentThread().getName());
            
            Integer carga = caminhao.getCarga();
            Integer tempoDemoraCarregar = carga * 2;
    
            try {
                caminhao.adicionaElementos(carga);
                System.out.println("dormindo o tempo de carregamento = " + tempoDemoraCarregar);
                Thread.currentThread().sleep(tempoDemoraCarregar * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            System.out.println("Caminhão carregando x toneladas da Azeitona y");
            
            this.notifyAll();
        }
        
    }


    public Integer getCarga() {
        return Carga;
    }


    public String getTipoDeAzeitona() {
        return tipoDeAzeitona;
    }

}
