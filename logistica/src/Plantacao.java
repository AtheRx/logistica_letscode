public class Plantacao extends Thread{
    private boolean isOcupado;
    private int distanciaEmS;
    private long tempoCarga;

    private Caminhao caminhao;
    
    public Plantacao(int distanciaEmS, long tempoCarga ){
        this.distanciaEmS = distanciaEmS;
        this.tempoCarga = tempoCarga;
    }

    public long getTempoCarga() {
        return tempoCarga;
    }
    public boolean carregaCaminhao(Caminhao caminhao){
        if( isOcupado){
            throw new RuntimeException("Ocupado");
        }

        this.caminhao = caminhao;
        return true;
    }
    @Override
    public void run() {
        isOcupado = true;
        System.out.println("Carregando...");
 
    }

    
    // ::run
    //     if !isOcupado
    //     :: carrega(Caminhao)
    //         >isOcupado = true
    //         >sleep(caminhao.fatorMultDaCarga )
    //         >tempoCarga
          
}
