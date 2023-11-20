package java20231120;

public class RecomendacaoAvaliacao {
    private static int recomendacaooIdContador = 1;
    private final int id;
    private Recomendacao recomendacao;
    private AvaliacaoValor valor;

    public RecomendacaoAvaliacao(Recomendacao recomendacao, AvaliacaoValor valor) {
        if( recomendacao == null || valor == null ){
            throw new IllegalArgumentException ("recomendacao e valor nao podem ser nulos");
        }
        this.id = recomendacaooIdContador++;
        this.recomendacao = recomendacao;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public Recomendacao getRecomendacao() {
        return recomendacao;
    }

    public AvaliacaoValor getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "RecomendacaoAvaliacao{" +
                "id=" + id +
                ", recomendacao=" + recomendacao.getId() +
                ", valor=" + valor +
                "}";
    }
}
