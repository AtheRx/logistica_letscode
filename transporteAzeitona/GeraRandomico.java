import java.util.Random;

public class GeraRandomico {
    public GeraRandomico() {
    }

    public static Integer randomico(Integer inferior, Integer superior) {
        Random numeroAleatorio = new Random();
        return numeroAleatorio.ints(inferior, (superior + 1)).findFirst().getAsInt();
    }

}
