import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Relatorio {
    protected static int toneladasAcumulada = 0;
    protected static LocalDateTime dataHora = LocalDateTime.now();

    public synchronized void gerar(Caminhao caminhao) {
        toneladasAcumulada = toneladasAcumulada + caminhao.getCarga();
        dataHora = dataHora.minusSeconds(1);
        DateTimeFormatter formatadorDataEHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String hora = dataHora.format(formatadorDataEHora);

        try {
            FileWriter fileWriter = new FileWriter("relatorio-1991.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf("%s - 0%d >> %d toneladas de %s com o tempo total de %d segundos\n",hora, toneladasAcumulada, caminhao.getCarga(), caminhao.getTipoCarga(), caminhao.getContador()/1000);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.printf("%s - 0%d >> %d toneladas de %s com o tempo total de %d segundos\n",hora, toneladasAcumulada, caminhao.getCarga(), caminhao.getTipoCarga(), caminhao.getContador()/1000);
    }
    
}
