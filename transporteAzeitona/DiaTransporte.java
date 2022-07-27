import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiaTransporte {
    private LocalDate dataTransporte;
    // criar o patter de conversão para o padrão

    public DiaTransporte(String data) {
        this.dataTransporte = LocalDate
                .parse(data, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public LocalDate getDataTransporte() {
        return dataTransporte;
    }

    public String getYearTransporte() {
        return Integer.valueOf(dataTransporte.getYear()).toString();
    }
}
