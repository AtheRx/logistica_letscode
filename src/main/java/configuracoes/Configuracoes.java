package configuracoes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Configuracoes {

    private static String pathArquivoRegras = "regras.txt";
    private static LocalDate dataAtual;
    private static long toneladasPorSegundo;
    private static List<String> tiposAzeitona = new ArrayList<>();

    private Configuracoes() {
    }

    public static LocalDate getDataAtual() {
        return dataAtual;
    }

    public static void carregar() {
        toneladasPorSegundo = Configuracoes.getToneladasPorSegundo();
        for (Map.Entry<String, Integer> item : Configuracoes.getVariedadeEQuantidade().entrySet()) {
            tiposAzeitona.add(item.getKey());
        }

    }

    public static String getData() {
        String regexData = "\\s+?\\d{2}\\/\\d{2}\\/\\d{4}";
        List<String> listaLinhas = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(pathArquivoRegras))) {
            stream.filter(linhaAtual -> linhaAtual.matches(regexData))
                    .forEach(linha -> listaLinhas.add(linha.trim()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile("\\s+?\\d{2}\\/\\d{2}\\/\\d{4}");
        Matcher matcher = pattern.matcher(listaLinhas.get(0));
        matcher.find();

        return listaLinhas.get(0);


    }

    public int getQuantidadeVariedades() {
        List<String> fraseCompativel = buscarPadrao("Variedades de Azeitonas:");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        int quantiVariedades = Integer.parseInt(matcher.get(0).group());
        return quantiVariedades;
    }

    public int getQuantidadePlantacoes() {
        List<String> fraseCompativel = buscarPadrao("Plantações de Azeitonas:");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        int quantiPlantacoes = Integer.parseInt(matcher.get(0).group());
        return quantiPlantacoes;
    }

    public static Map<String, Integer> getVariedadeEQuantidade() {
        String padraoProcuradoVariedadeEQtd = "plantações de ";
        String padraoProcuradoVariedadeEQtd2 = "plantação de ";
        List<String> listaLinhas = new ArrayList<>();
        Map<String, Integer> variedadeEQtd = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get(pathArquivoRegras))) {
            stream.map(linha -> linha.trim())
                    .filter(linhaAtual -> linhaAtual.contains(padraoProcuradoVariedadeEQtd) || linhaAtual.contains(padraoProcuradoVariedadeEQtd2))
                    .forEach(linhaMatche -> listaLinhas.add(linhaMatche));
        } catch (IOException e) {
            e.printStackTrace();
        }

        listaLinhas.stream().forEach(linha -> {
            int quant = Integer.parseInt(Character.toString(linha.charAt(0)));
            String variedade;
            String substring = linha.substring(linha.indexOf("de ") + 2).trim();
            variedade = substring.substring(0, substring.indexOf(" "));
            variedadeEQtd.put(variedade, quant);
        });

        return variedadeEQtd;

    }

    public static Map<String, Integer> getVariedadeEDistancia() {
        String padraoProcuradoVariedadeEDistancia = "plantações de ";
        String padraoProcuradoVariedadeEDist2 = "plantação de ";
        List<String> listaLinhas = new ArrayList<>();
        Map<String, Integer> variedadeEDist = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get(pathArquivoRegras))) {
            stream.map(linha -> linha.trim())
                    .filter(linhaAtual -> linhaAtual.contains(padraoProcuradoVariedadeEDist2) || linhaAtual.contains(padraoProcuradoVariedadeEDistancia))
                    .forEach(linhaMatche -> listaLinhas.add(linhaMatche));
        } catch (IOException e) {
            e.printStackTrace();
        }

        listaLinhas.stream().forEach(linha -> {
            int distanciaSegundos;
            String variedade;
            String subString = linha.substring(linha.indexOf("segundos") - 2).trim();
            String substring = linha.substring(linha.indexOf("de ") + 2).trim();

            distanciaSegundos = Integer.parseInt(Character.toString(subString.charAt(0)));
            variedade = substring.substring(0, substring.indexOf(" "));
            variedadeEDist.put(variedade, distanciaSegundos);
        });

        return variedadeEDist;

    }

    public static long getToneladasPorSegundo() {
        List<Integer> listaToneladasPorSegundos = new ArrayList<>();
        List<String> fraseCompativel = buscarPadrao("corresponde a");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        //segundos
        listaToneladasPorSegundos.add(Integer.parseInt(matcher.get(0).group()));
        //toneladas
        listaToneladasPorSegundos.add(Integer.parseInt(matcher.get(1).group()));

        return listaToneladasPorSegundos.get(0);
    }


    public static List<Integer> getCapacidadeDeTransporte() {
        List<Integer> capacidadeTransCaminhao = new ArrayList<>();
        List<String> fraseCompativel = buscarPadrao("Varia entre");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        capacidadeTransCaminhao.add(Integer.parseInt(matcher.get(0).group()));
        capacidadeTransCaminhao.add(Integer.parseInt(matcher.get(1).group()));
        return capacidadeTransCaminhao;
    }

    public static int getCapacidadeMinimaDaFila() {
        List<String> fraseCompativel = buscarPadrao("plantações podem enviar mais");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        int capacidadeMinimaDaFila = Integer.parseInt(matcher.get(0).group());
        return capacidadeMinimaDaFila;
    }

    public static int getCapacidadeMaximaDaFila() {
        List<String> fraseCompativel = buscarPadrao("plantações param");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        int capacidadeMaximaDaFila = Integer.parseInt(matcher.get(0).group());
        return capacidadeMaximaDaFila;
    }

    public static int getCapacidadeDeRecepcaoSimultanea() {
        List<String> listaLinhas = buscarPadrao("Capacidade");
        int capacidadeDeRecepcaoSimultanea = Integer.parseInt(Character.toString(listaLinhas.get(0).charAt(0)));
        return capacidadeDeRecepcaoSimultanea;

    }

    public static List<String> buscarPadrao(String padrao) {
        List<String> listaLinhas = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(pathArquivoRegras))) {
            stream.filter(linhaAtual -> linhaAtual.contains(padrao))
                    .forEach(linha -> listaLinhas.add(linha.trim()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaLinhas;
    }
    
    public static String nomeRelatorio() {
        String nome;
        String data = getData().substring(6, 10);
        nome = "relatorio-" + data + ".txt";
        return nome;
    }
}
