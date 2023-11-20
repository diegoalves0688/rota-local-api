package java20231120;

public class Localizacao {
    private static int localizacaoIdContador = 1;
    private final int id;
    private final String pais;
    private final String estado;
    private final String cidade;
    private final String cep;

    public Localizacao(String pais, String estado, String cidade, String cep) {
        this.id = localizacaoIdContador++;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return pais + ", " + estado + ", " + cidade + ", " + cep;
    }
}
