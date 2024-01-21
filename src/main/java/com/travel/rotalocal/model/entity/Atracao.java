package com.travel.rotalocal.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel.rotalocal.model.EstadoAtivo;
import com.travel.rotalocal.model.EstadoAtracao;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "atracao", schema = "public")
public class Atracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaAtracao categoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusAtracao status;

    @CreationTimestamp
    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "localizacao_id", referencedColumnName = "id")
    private Localizacao localizacao;

    @JsonIgnore
    @OneToMany(mappedBy = "atracao", cascade = CascadeType.ALL)
    private List<Imagem> imagens;

    @JsonIgnore
    @OneToMany(mappedBy = "atracao", cascade = CascadeType.ALL)
    private List<RecomendacaoAtracao> recomendacoesAtracoes;

    @JsonIgnore
    @OneToMany(mappedBy = "atracao", cascade = CascadeType.ALL)
    private List<AvaliacaoAtracao> avaliacoesAtracoes;

    // STATE
    @Transient
    private EstadoAtracao estadoAtracao;

    public Atracao() {
        this.ativo = true;
        this.estadoAtracao = new EstadoAtivo();
    }

    public void ativarAtracao() {
        estadoAtracao.ativarAtracao(this);
        this.ativo = true;
    }

    public void inativarAtracao() {
        estadoAtracao.inativarAtracao(this);
        this.ativo = false;
    }

    public void setEstadoAtracao(EstadoAtracao estadoAtracao) {
        this.estadoAtracao = estadoAtracao;
    }

    public boolean ativo() {
        return ativo;
    }

}
