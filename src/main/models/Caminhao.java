package main.models;

public class Caminhao {
    private Integer carga = 6;

    public synchronized void adicionaElementos(Integer cargaAdiconada) {
        this.carga = cargaAdiconada;
        
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer getCarga() {
        return carga;
    }

    
}
