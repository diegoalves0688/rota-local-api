package java20231120;

public class AtracaoAvaliacao {
    private static int avaliacaoIdContador = 1;
    private final int id;
    private Atracao atracao;
    private AvaliacaoValor valor;

    public AtracaoAvaliacao(Atracao atracao, AvaliacaoValor valor) {
        if( atracao == null || valor == null ){
            throw new IllegalArgumentException ("atracao e valor nao podem ser nulos");
        }
        this.id = avaliacaoIdContador++;
        this.atracao = atracao;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public Atracao getAtracao() {
        return atracao;
    }

    public AvaliacaoValor getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "AtracaoAvaliacao{" +
                "id=" + id +
                ", atracao=" + atracao.getId() +
                ", valor=" + valor +
                "}";
    }
}
