public class Plantacao{
    private boolean isOcupado;
    private long distanciaEmS;
    private long tempoDeCarga;

    private Caminhao caminhao;
    
    public Plantacao(int distanciaEmS, long tempoCarga ){
        this.distanciaEmS = distanciaEmS;
        this.tempoDeCarga = tempoCarga;
    }

    public long getTempoCarga() {
        return tempoDeCarga;
    }
    public void carregaCaminhao(Caminhao caminhao){
        this.caminhao = caminhao;
        if( this.isOcupado){
            throw new RuntimeException("Ocupado");
        }

        new Thread(this.caminhao.getId()) {

            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                try {
                    System.out.println(threadName + " Carregando...");
                    Thread.sleep(tempoDeCarga*1000);
                    System.out.println(threadName + " Carregado!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                
            }
            
        }.start();
        
        
    }




    
    // ::run
    //     if !isOcupado
    //     :: carrega(Caminhao)
    //         >isOcupado = true
    //         >sleep(caminhao.fatorMultDaCarga )
    //         >tempoCarga
          
}
