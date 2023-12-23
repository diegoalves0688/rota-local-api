package com.travel.rotalocal.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.travel.rotalocal.model.entity.CategoriaAtracao;
import com.travel.rotalocal.model.entity.StatusAtracao;

public class AtracaoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private boolean ativo;
    private CategoriaAtracao categoria;
    private StatusAtracao status;
    private LocalDateTime dataRegistro;
    private UsuarioDTO usuario;
    private LocalizacaoDTO localizacao;
    private List<ImagemDTO> imagens;

    private List<AvaliacaoAtracaoDTO> avaliacoesAtracoes;
    private int avaliacaoSaldoPontos;
    private int atracaoRanking;

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

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public UsuarioDTO getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public LocalizacaoDTO getLocalizacao() {
        return this.localizacao;
    }

    public void setLocalizacao(LocalizacaoDTO localizacao) {
        this.localizacao = localizacao;
    }

    public List<ImagemDTO> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemDTO> imagens) {
        this.imagens = imagens;
    }

    public List<AvaliacaoAtracaoDTO> getAvaliacoesAtracoes() {
        return avaliacoesAtracoes;
    }

    public void setAvaliacoesAtracoes(List<AvaliacaoAtracaoDTO> avaliacoesAtracoes) {
        this.avaliacoesAtracoes = avaliacoesAtracoes;
    }

    public int getAvaliacaoSaldoPontos() {
        return avaliacaoSaldoPontos;
    }

    public void setAvaliacaoSaldoPontos(int avaliacaoSaldoPontos) {
        this.avaliacaoSaldoPontos = avaliacaoSaldoPontos;
    }

    public int getAtracaoRanking() {
        return atracaoRanking;
    }

    public void setAtracaoRanking(int atracaoRanking) {
        this.atracaoRanking = atracaoRanking;
    }

}
