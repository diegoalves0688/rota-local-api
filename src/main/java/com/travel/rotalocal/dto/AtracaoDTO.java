package com.travel.rotalocal.dto;

import java.util.List;

import com.travel.rotalocal.model.entity.CategoriaAtracao;
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
    private List<ImagemDTO> imagens;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public CategoriaAtracao getCategoria() {
        return this.categoria;
    }

    public void setCategoria(CategoriaAtracao categoria) {
        this.categoria = categoria;
    }

    public StatusAtracao getStatus() {
        return this.status;
    }

    public void setStatus(StatusAtracao status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Localizacao getLocalizacao() {
        return this.localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public List<ImagemDTO> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemDTO> imagens) {
        this.imagens = imagens;
    }

}

