package java20231120;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private static int usuarioIdContador = 1;
    private final int id;
    private final String email;
    private final String nome;
    private final String senha;
    private Perfil perfil;
    private boolean estaAtivo;
    private List<Atracao> atracoesCriadas;
    private List<AtracaoAvaliacao> atracoesAvaliadas;
    private List<Recomendacao> recomendacoesEscritas;
    private List<RecomendacaoAvaliacao> recomendacoesAvaliadas;

//    private List<Localizacao> localizacoesCriadas;

    public Usuario(String email, String nome, String senha) {
        if (email == null || nome == null || senha == null) {
            throw new IllegalArgumentException("email, nome, senha nao podem ser nulos ");
        }
        this.id = usuarioIdContador++;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.perfil = Perfil.COLABORADOR; // perfil colaborador como default
        this.estaAtivo = true; // add por causa do soft delete

        this.atracoesCriadas = new ArrayList<>();
        this.atracoesAvaliadas = new ArrayList<>();
        this.recomendacoesEscritas = new ArrayList<>();
        this.recomendacoesAvaliadas = new ArrayList<>();
//        this.localizacoesCriadas = new ArrayList<>();
    }

    //    public List<Localizacao> getLocalizacoesCriadas() {
//        return localizacoesCriadas;
//    }
    public List<Atracao> getAtracoesCriadas() {
        return atracoesCriadas;
    }
    public List<AtracaoAvaliacao> getAtracoesAvaliadas() {
        return atracoesAvaliadas;
    }

    public List<Recomendacao> getRecomendacoesEscritas() {
        return recomendacoesEscritas;
    }

    public List<RecomendacaoAvaliacao> getRecomendacoesAvaliadas() {
        return recomendacoesAvaliadas;
    }

    public int getId() {
        return id;
    }
    public Perfil getPerfil() {
        return perfil;
    }
    public String getEmail() {
        return email;
    }
    public String getNome() {
        return nome;
    }
    public boolean estaAtivo() {
        return estaAtivo;
    } // add pq pode ter o soft delete

    //TODO: REVER LOGICA PARA ATULIZAR PERFIL
    public void atualizarPerfil(Perfil novoPerfil) {
        if (perfil == Perfil.COLABORADOR) {
            this.perfil = novoPerfil;
        } else {
            System.out.println("colaboradores comuns nao tem permissao para esta acao ");
        }
    }

    // desativar para simular o "soft delete"
    public void desativarConta() {
        this.estaAtivo = false;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", perfil=" + perfil +
                ", estaAtivo=" + estaAtivo +
                "}";
    }
}
