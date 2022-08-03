public class Caminhao {
    private int contador = 0;
    private int carga;
    private String tipoCarga;
    private int tempoCarga;
    private int tempoDescarga;
    private int distanciaDoLagar;
    private String data;


    private Caminhao(CaminhaoBuilder builder) {
        this.carga = builder.carga;
        this.tempoCarga = builder.tempoCarga;
        this.tempoDescarga = builder.tempoDescarga;
        this.distanciaDoLagar = builder.distanciaDoLagar;
        this.tipoCarga = builder.tipoCarga;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    public int getTempoCarga() {
        return tempoCarga;
    }

    public void setTempoCarga(int tempoCarga) {
        this.tempoCarga = tempoCarga;
    }

    public int getTempoDescarga() {
        return tempoDescarga;
    }

    public void setTempoDescarga(int tempoDescarga) {
        this.tempoDescarga = tempoDescarga;
    }

    public int getDistanciaDoLagar() {
        return distanciaDoLagar;
    }

    public void setDistanciaDoLagar(int distanciaDoLagar) {
        this.distanciaDoLagar = distanciaDoLagar;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public static class CaminhaoBuilder {
        private int contador = 0;
        private int carga;
        private String tipoCarga;
        private int tempoCarga;
        private int tempoDescarga;
        private int distanciaDoLagar;
        private String data;


        public CaminhaoBuilder carga(int carga) {
            this.carga = carga;
            return this;
        }

        public CaminhaoBuilder tipoCarga(String tipoCarga) {
            this.tipoCarga = tipoCarga;
            return this;
        }

        public CaminhaoBuilder tempocarga(int tempoCarga) {
            this.tempoCarga = tempoCarga;
            return this;
        }

        public CaminhaoBuilder tempodescarga(int tempoDescarga) {
            this.tempoDescarga = tempoDescarga;
            return this;
        }

        public CaminhaoBuilder distanciadolagar(int distanciaDoLagar) {
            this.distanciaDoLagar = distanciaDoLagar;
            return this;
        }

        public CaminhaoBuilder data(String data) {
            this.data = data;
            return this;
        }


        public Caminhao build() {
            Caminhao caminhao = new Caminhao(this);
            return caminhao;
        }

    }
}
