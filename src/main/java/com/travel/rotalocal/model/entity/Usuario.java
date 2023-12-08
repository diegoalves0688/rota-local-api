package com.travel.rotalocal.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Table(name = "usuario", schema = "public")
public class Usuario {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "foto", nullable = false)
    private String foto;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false) 
    private PerfilUsuario perfil;

    @JsonIgnore 
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL) 
    private List<Atracao> atracoes;

    @JsonIgnore 
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL) 
    private List<Imagem> imagens;

    @JsonIgnore 
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL) 
    private List<RecomendacaoAtracao> recomendacoesAtracoes;

    @JsonIgnore 
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL) 
    private List<AvaliacaoAtracao> avaliacoesAtracoes;

    @JsonIgnore 
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL) 
    private List<AvaliacaoRecomendacao> avaliacoesRecomendacoes;

}
