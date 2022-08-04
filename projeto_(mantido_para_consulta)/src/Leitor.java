import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Leitor implements LeitorInterface {
    private String pathArquivo = "projeto\\regras.txt";

    @Override
    public String getData() {
        String regexData = "\\s+?\\d{2}\\/\\d{2}\\/\\d{4}";
        List<String> listaLinhas = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(pathArquivo))) {
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

    @Override
    public int getQuantidadeVariedade() {
        List<String> fraseCompativel = buscarPadrao("Variedades de Azeitonas:");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        int quantiVariedades = Integer.parseInt(matcher.get(0).group());
        return quantiVariedades;
    }

    @Override
    public int getQuantidadePlantacoes() {
        List<String> fraseCompativel = buscarPadrao("Plantações de Azeitonas:");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        int quantiPlantacoes = Integer.parseInt(matcher.get(0).group());
        return quantiPlantacoes;
    }

    @Override
    public Map<String, Integer> getVariedadeEQtd() {
        String padraoProcuradoVariedadeEQtd = "plantações de ";
        String padraoProcuradoVariedadeEQtd2 = "plantação de ";
        List<String> listaLinhas = new ArrayList<>();
        Map<String, Integer> variedadeEQtd = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get(pathArquivo))) {
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

    @Override
    public Map<String, Integer> getVariedadeEDist() {
        String padraoProcuradoVariedadeEDist = "plantações de ";
        String padraoProcuradoVariedadeEDist2 = "plantação de ";
        List<String> listaLinhas = new ArrayList<>();
        Map<String, Integer> variedadeEDist = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get(pathArquivo))) {
            stream.map(linha -> linha.trim())
                    .filter(linhaAtual -> linhaAtual.contains(padraoProcuradoVariedadeEDist2) || linhaAtual.contains(padraoProcuradoVariedadeEDist))
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

    @Override
    public int getQtdRecepcao() {
        List<String> listaLinhas = buscarPadrao("Capacidade");
        int qtdRecepecao = Integer.parseInt(Character.toString(listaLinhas.get(0).charAt(0)));
        return qtdRecepecao;

    }

    @Override
    public List<Integer> getCapacidadeTransCaminhao() {
        List<Integer> capacidadeTransCaminhao = new ArrayList<>();
        List<String> fraseCompativel = buscarPadrao("Varia entre");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        capacidadeTransCaminhao.add(Integer.parseInt(matcher.get(0).group()));
        capacidadeTransCaminhao.add(Integer.parseInt(matcher.get(1).group()));
        return capacidadeTransCaminhao;
    }

    @Override
    public int getLimiteSupEsperaNoLagar() {
        List<String> fraseCompativel = buscarPadrao("plantações param");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        int limiteSupEsperaNoLagar = Integer.parseInt(matcher.get(0).group());
        return limiteSupEsperaNoLagar;
    }


    @Override
    public int getLimiteInfParaVoltarAOperar() {
        List<String> fraseCompativel = buscarPadrao("plantações podem enviar mais");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        int limiteInfParaVoltarAOperar = Integer.parseInt(matcher.get(0).group());
        return limiteInfParaVoltarAOperar;
    }

    @Override
    public List<Integer> getCapacidadeDeCarga() {
        List<Integer> listaCapacidadeDeCarga = new ArrayList<>();
        List<String> fraseCompativel = buscarPadrao("enche");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());

        listaCapacidadeDeCarga.add(Integer.parseInt(matcher.get(0).group()));
        listaCapacidadeDeCarga.add(Integer.parseInt(matcher.get(1).group()));

        return listaCapacidadeDeCarga;
    }

    @Override
    public List<Integer> getCapacidadeDeDescarga() {
        List<Integer> listaCapacidadeDeDescarga = new ArrayList<>();
        List<String> fraseCompativel = buscarPadrao("recepção ");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());

        listaCapacidadeDeDescarga.add(Integer.parseInt(matcher.get(0).group()));
        listaCapacidadeDeDescarga.add(Integer.parseInt(matcher.get(1).group()));


        return listaCapacidadeDeDescarga;
    }


    @Override
    public List<Integer> getFatorMultiplicador() {
        List<Integer> listaFatorMultiplicador = new ArrayList<>();
        List<String> fraseCompativel = buscarPadrao("corresponde a");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());

        listaFatorMultiplicador.add(Integer.parseInt(matcher.get(0).group()));
        listaFatorMultiplicador.add(Integer.parseInt(matcher.get(1).group()));


        return listaFatorMultiplicador;
    }


    @Override
    public int getLimiteDeInterrupcaoDaCarga() {
        List<String> fraseCompativel = buscarPadrao("execução geral");
        Pattern pattern = Pattern.compile("\\d+");
        List<MatchResult> matcher = pattern.matcher(fraseCompativel.get(0)).results().collect(Collectors.toList());
        int limiteDeInterrupcaoDaCarga = Integer.parseInt(matcher.get(0).group());
        return limiteDeInterrupcaoDaCarga;

    }


    public List<String> buscarPadrao(String padrao) {
        List<String> listaLinhas = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(pathArquivo))) {
            stream.filter(linhaAtual -> linhaAtual.contains(padrao))
                    .forEach(linha -> listaLinhas.add(linha.trim()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaLinhas;

    }


}