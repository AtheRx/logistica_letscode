package models;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Relatorio {
    private FileWriter fileWriter;
    PrintWriter printWriter;

    public Relatorio(String pathDeSaida) {
        try {
            this.fileWriter = new FileWriter(pathDeSaida);
            this.printWriter = new PrintWriter(this.fileWriter);
            System.out.println("Registro aberto.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void imprime(String registro) {
        printWriter.printf(registro);
        //System.out.printf(registro);
    }

    public void fecharRegistro() throws IllegalStateException {
        System.out.println("Registro fechado.");
        printWriter.close();
    }

}
