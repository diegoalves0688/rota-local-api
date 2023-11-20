package java20231120;

import java.util.ArrayList;
import java.util.List;

public class Recomendacao {
    private static int recomendacaoIdContador = 1;
    private final int id;
    private String conteudo;
    private List<RecomendacaoAvaliacao> avaliacoes;
    private Usuario criadoPor;

    public Recomendacao(String conteudo,  Usuario criadoPor) {
        if (conteudo == null ) {
            throw new IllegalArgumentException("conteudo e avaliacao nao podem ser nulos");
        }
        this.id = recomendacaoIdContador++;
        this.conteudo = conteudo;
        this.avaliacoes = new ArrayList<>();
        this.criadoPor = criadoPor;
    }
    public Usuario getCriadoPor() {
        return criadoPor;
    }

    public int getId() {
        return id;
    }

    public void addAvaliacao(RecomendacaoAvaliacao recomendacaoAvaliacao) {
        this.avaliacoes.add(recomendacaoAvaliacao);
    }
    private List<Integer> getAvaliacaoIds() {
        List<Integer> avaliacaoIds = new ArrayList<>();
        for (RecomendacaoAvaliacao recomendacaoAvaliacao : avaliacoes) {
            avaliacaoIds.add(recomendacaoAvaliacao.getId());
        }
        return avaliacaoIds;
    }

    public static int getRecomendacaoIdContador() {
        return recomendacaoIdContador;
    }
    public String getConteudo() {
        return conteudo;
    }
    public List<RecomendacaoAvaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    @Override
    public String toString() {
        return "Recomendacao{" +
                "id=" + id +
                ", conteudo='" + conteudo + '\'' +
                ", avaliacoes=" + getAvaliacaoIds() +
                "}";
    }
}
