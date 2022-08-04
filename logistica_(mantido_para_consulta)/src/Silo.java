public class Silo extends Thread {
    private int tempoDescarga;
    private boolean isOcupado;

    @Override
    public void run() {
        System.out.println("Descarregando");
    }


// ::run
// 	if !isOcupado
// 	:: descarrega(Caminhao)
// 		>isOcupado = true
// 		>sleep(caminhao.fatorMultDaDescarga )
// 		>tempoDescarga
}
