package com.travel.rotalocal.api.dto;

import java.util.List;

import com.travel.rotalocal.model.entity.CategoriaAtracao;
import com.travel.rotalocal.model.entity.Imagem;
import com.travel.rotalocal.model.entity.Localizacao;
import com.travel.rotalocal.model.entity.StatusAtracao;
import com.travel.rotalocal.model.entity.Usuario;


public class AtracaoDTO {
    
    private Long id;
    private String nome;
    private String descricao;
    private boolean ativo;
    private CategoriaAtracao categoria;
    private StatusAtracao status;
    private Usuario usuario;
    private Localizacao localizacao;
    private List<Imagem> imagens;

    public AtracaoDTO(Long id, String nome, String descricao, boolean ativo, CategoriaAtracao categoria,
            StatusAtracao status, Usuario usuario, Localizacao localizacao, List<Imagem> imagens) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.categoria = categoria;
        this.status = status;
        this.usuario = usuario;
        this.localizacao = localizacao;
        this.imagens = imagens;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public CategoriaAtracao getCategoria() {
        return categoria;
    }
    public void setCategoria(CategoriaAtracao categoria) {
        this.categoria = categoria;
    }
    public StatusAtracao getStatus() {
        return status;
    }
    public void setStatus(StatusAtracao status) {
        this.status = status;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Localizacao getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
    public List<Imagem> getImagens() {
        return imagens;
    }
    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

}
