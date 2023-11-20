package java20231120;


import java.util.ArrayList;
import java.util.List;

public class Atracao {
    private static int atracaoIdContador = 1;
    private final int id;
    private String nome;
    private String descricao;
    private String foto;
    private Localizacao localizacao;
    private Categoria categoria;
    private Status status;
    private List<Recomendacao> recomendacoes;
    private List<AtracaoAvaliacao> avaliacoes;
    private Usuario criadoPor;

    public Atracao(String nome, String descricao, String foto, Localizacao localizacao, Categoria categoria,
                   Usuario criadoPor) {
        if (nome == null || descricao == null || foto == null || localizacao == null || categoria == null) {
            throw new IllegalArgumentException("nome, descricao, foto, localizacao e categoria nao podem ser nulos.");
        }
        this.id = atracaoIdContador++;
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.localizacao = localizacao;
        this.categoria = categoria;
        this.status = Status.PUBLICO; // valor default
        this.recomendacoes = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
        this.criadoPor = criadoPor;
    }

    public int getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addRecomendacao(Recomendacao recomendacao) {
        this.recomendacoes.add(recomendacao);
    }

    public void addAvaliacao(AtracaoAvaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public String getNome() {
        return nome;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public Status getStatus() {
        return status;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    private List<Integer> getRecomendacoesIds() {
        List<Integer> recomendacaosIds = new ArrayList<>();
        for (Recomendacao recomendacao : recomendacoes) {
            recomendacaosIds.add(recomendacao.getId());
        }
        return recomendacaosIds;
    }

    private List<Integer> getAvaliacoesIds() {
        List<Integer> avaliacaoIds = new ArrayList<>();
        for (AtracaoAvaliacao avaliacao : avaliacoes) {
            avaliacaoIds.add(avaliacao.getId());
        }
        return avaliacaoIds;
    }

    @Override
    public String toString() {
        return "Atracao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", foto='" + foto + '\'' +
                ", localizacao=" + localizacao +
                ", categoria=" + categoria +
                ", status=" + status +
                ", avaliacoes=" + getAvaliacoesIds() +
                ", recomedacoes=" + getRecomendacoesIds() +
                "}";
    }

    public Usuario getCriadoPor() {
        return criadoPor;
    }

    public List<AtracaoAvaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public List<Recomendacao> getRecomendacoes() {
        return recomendacoes;
    }
}