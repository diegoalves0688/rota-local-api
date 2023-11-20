package java20231120;

import java.util.ArrayList;
import java.util.List;

public class SistemaAtracao {
    private List<Usuario> usuarios;
    private List<Atracao> atracoes;
    private List<AtracaoAvaliacao> atracaoAvaliacoes;
    private List<RecomendacaoAvaliacao> recomendacaoAvaliacoes;
    //    private List<Localizacao> localizacoes;
    public SistemaAtracao() {
        this.usuarios =new ArrayList<>();
        this.atracoes =new ArrayList<>();
        this.atracaoAvaliacoes = new ArrayList<>();
        this.recomendacaoAvaliacoes = new ArrayList<>();
//        this.localizacoes =new ArrayList<>(); //20231120
    }

    //metodos usuarios, perfis
    public void registrarUsuario(String email, String nome, String senha){
        Usuario usuario = new Usuario(email, nome, senha);
        usuarios.add(usuario);
    }
    public void atualizarPerfilUsuario(int usuarioId, Perfil novoPerfil){
        Usuario usuario = getUsuarioPorId(usuarioId);
        if( usuario != null ){
            usuario.atualizarPerfil(novoPerfil);
        }
    }

    //metodos atracoes
    public void criarAtracao(int usuarioId, String nome, String descricao, String foto, Localizacao localizacao, Categoria categoria){
        Usuario usuario = getUsuarioPorId(usuarioId);
        if( usuario != null && usuario.getPerfil() == Perfil.COLABORADOR){
            Atracao atracao = new Atracao(nome, descricao, foto, localizacao, categoria,usuario);
            atracoes.add(atracao);

            usuario.getAtracoesCriadas().add(atracao);
        }

    }

    public void avaliarAtracao(int usuarioId, int atracaoId, AvaliacaoValor valor){
        Usuario usuario = getUsuarioPorId(usuarioId);
        Atracao atracao = getAtracaoPorId(atracaoId);

        if (usuario != null && atracao != null) {
            AtracaoAvaliacao avaliacao = new AtracaoAvaliacao(atracao, valor);
            atracao.addAvaliacao(avaliacao);
            atracaoAvaliacoes.add(avaliacao);

            usuario.getAtracoesAvaliadas().add(avaliacao);
        }
    }

    public void escreverRecomendacao(int usuarioId, int atracaoId, String conteudo) {//, AvaliacaoValor avaliacaoValor
        Usuario usuario = getUsuarioPorId(usuarioId);
        Atracao atracao = getAtracaoPorId(atracaoId);

        if (usuario != null && atracao != null) {
            Recomendacao recomendacao  = new Recomendacao(conteudo, usuario);//avaliacaoValor
            atracao.addRecomendacao(recomendacao);
//            RecomendacaoAvaliacao recomendacaoAvaliacao = new RecomendacaoAvaliacao(recomendacao);//avaliacaoValor
//            recomendacao.addAvaliacao(recomendacaoAvaliacao);
//            recomendacaoAvaliacoes.add(recomendacaoAvaliacao);

            usuario.getRecomendacoesEscritas().add(recomendacao);
        }
    }

    public void avaliarRecomendacao(int usuarioId, int recomendacaoId, AvaliacaoValor valor){
        Usuario usuario = getUsuarioPorId(usuarioId);
        Recomendacao recomendacao = getRecomendacaoPorId(recomendacaoId);

        if (usuario != null && recomendacao != null) {
            RecomendacaoAvaliacao recomendacaoAvaliacao = new RecomendacaoAvaliacao(recomendacao, valor);
            recomendacao.addAvaliacao(recomendacaoAvaliacao);
            recomendacaoAvaliacoes.add(recomendacaoAvaliacao);

            usuario.getRecomendacoesAvaliadas().add(recomendacaoAvaliacao);
        }
    }

    //resumir - simular relatorio aka "cereja do bolo"
    public void resumirPorAtracao() {
        System.out.println("\n--------------------------- relatorio por atracao turistica -----------------------------");
        System.out.printf("%-30s%-15s%-15s%-15s%-25s%-25s%-25s%n",
                "nome da atracao ", "criado por", "status", "categoria",
                "# de avaliacoes ", " # avaliacoes like ", "# de recomendacoes ");

        for (Atracao atracao : atracoes) {
            int totalRatings = atracao.getAvaliacoes().size();
            int totalPositiveRatings = getQuantidadeAvaliacoesPositivas(atracao);
            int totalReviews = (int) getQuantidadeTotalRecomendacoes(atracao);

            System.out.printf("%-30s%-15s%-15s%-15s%-25s%-25s%-25s%n",
                    atracao.getNome(), atracao.getCriadoPor().getNome(),
                    atracao.getStatus(), atracao.getCategoria(),
                    totalRatings, totalPositiveRatings, totalReviews);
        }
    }

    public void resumirPorUsuario() {
        System.out.println("\n--------------------------- relatorio por usuario -----------------------------");
        System.out.printf("%-5s%-15s%-30s%-35s%-25s%-15s%-15s%n",
                "id", "nome", "# atracoes criadas",
                "# avaliacoes dadas de atracao", "# avaliacoes dadas de recomendacao",
                "# recomendacoes escritas ", "perfil");

        for (Usuario usuario : usuarios) {
            int atracoesCriadasQuantidade = usuario.getAtracoesCriadas().size();
            int avaliacoesDadasParaAtracoes = usuario.getAtracoesAvaliadas().size();
            int avaliacoesDadasParaRecomendacoes = usuario.getRecomendacoesAvaliadas().size();
            int recomendacoesCriadasQuantidade = usuario.getRecomendacoesEscritas().size();

            System.out.printf("%-5d%-15s%-30d%-35d%-25d%-15d%-15s%n",
                    usuario.getId(), usuario.getNome(), atracoesCriadasQuantidade,
                    avaliacoesDadasParaAtracoes, avaliacoesDadasParaRecomendacoes,
                    recomendacoesCriadasQuantidade, usuario.getPerfil());
        }
    }

    //metodos auxiliares

    private int getQuantidadeAvaliacoesPositivas(Atracao atracao) {
        return (int) atracao.getAvaliacoes().stream()
                .filter(avaliacao -> avaliacao.getValor() == AvaliacaoValor.LIKE)
                .count();
    }

    private long getQuantidadeTotalRecomendacoes(Atracao atracao) {
        return atracao.getRecomendacoes().stream().count();
    }

    private Usuario getUsuarioPorId(int usuarioId) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId() == usuarioId)
                .findFirst()
                .orElse(null);
    }

    private Atracao getAtracaoPorId(int atracaoId) {
        return atracoes.stream()
                .filter(atracao -> atracao.getId() == atracaoId)
                .findFirst()
                .orElse(null);
    }

    private Recomendacao getRecomendacaoPorId(int recomendacaoId) {
        return recomendacaoAvaliacoes.stream()
                .filter(recomendacaoAvaliacao -> recomendacaoAvaliacao.getId() == recomendacaoId)
                .findFirst()
                .map(RecomendacaoAvaliacao::getRecomendacao)
                .orElse(null);
    }

    /*****************************************************************************************************************
     *                                                        MAIN
     *****************************************************************************************************************/

    public static void main(String[] args) {

        SistemaAtracao sistemaAtracao = new SistemaAtracao();

        System.out.println("\n------- TESTE | INICIO ------");
        // cadastro usuarios
        sistemaAtracao.registrarUsuario("ana@gmail", "Ana", "ana_pwd");
        sistemaAtracao.registrarUsuario("joao@gmail", "Joao", "joao_pwd");
        sistemaAtracao.registrarUsuario("lud@gmail", "Lud", "lud_pwd");
        sistemaAtracao.registrarUsuario("ze@gmail", "Ze", "ze_pwd");
        System.out.println(" usuarios criados.....");

        // atualizar perfil de usuario
        sistemaAtracao.atualizarPerfilUsuario(3, Perfil.ADMNISTRADOR);
        System.out.println(" perfil atualizado.....");

        // criar localizacao
        // FIXME - entender se usuario ira criar localizacao
//        sistemaAtracao.criarLocalizacao(3,"brasil", "SP", "Sao Paulo", "1111");
//        sistemaAtracao.criarLocalizacao(3,"brasil", "RJ", "Rio de Janeiro", "22222");
//        sistemaAtracao.criarLocalizacao(3, "brasil", "CE", "Fortaleza", "33333");

        // criar atracao
        sistemaAtracao.criarAtracao(1, "cristo redentor", "estatua supimpa", "foto",
                new Localizacao("brasil", "rj", "rio de janeiro", "666"), Categoria.MONUMENTOS);//ana cria atracao
        sistemaAtracao.criarAtracao(4, "masp", "museu bacana", "photo",
                new Localizacao("brasil", "sp", "sao paulo", "123"), Categoria.MUSEUS);//ze cria atracao
        System.out.println(" atracao criada .....");


        // avaliar atracao
        sistemaAtracao.avaliarAtracao(2, 1, AvaliacaoValor.LIKE); // joao avalia cristo com joinha
        sistemaAtracao.avaliarAtracao(3, 1, AvaliacaoValor.LIKE); // lud avalia cristo com joinha
        sistemaAtracao.avaliarAtracao(4, 1, AvaliacaoValor.DISLIKE); // ze avalia cristo negativamente
        System.out.println(" atracao avaliada .....");
        //FIXME - bloquear para que usuario so consiga avaliar uma vez


        // escrever recomendacao
        sistemaAtracao.escreverRecomendacao(2, 1, "estive lá antes da onda de calor, foi mt bom"); // joao escreve recomendacao AvaliacaoValor.LIKE
        sistemaAtracao.escreverRecomendacao(1, 2, "um dos melhores"); //ana escreve sobre masp
        System.out.println(" recomendacao escrita .....");

        // avaliar recomendacao
        sistemaAtracao.avaliarRecomendacao(3, 1, AvaliacaoValor.DISLIKE); // lud dá dislike no texto do joao
        sistemaAtracao.avaliarRecomendacao(3, 2, AvaliacaoValor.LIKE); // lud dá dislike no texto da ana
        //FIXME - # avaliacao recomendacao nao aparece no relatorio por usuario

        //FIXME - bloquear para que so possa ser avaliado o que existe
//        sistemaAtracao.avaliarRecomendacao(3, 9, AvaliacaoValor.DISLIKE); // lud dá dislike no texto da ze que nao existe

        System.out.println(" recomendacao avaliada .....");

        // conferir resumo por atracao
        sistemaAtracao.resumirPorAtracao();

        // conferir resumo por usuario
        sistemaAtracao.resumirPorUsuario();
    }
}
