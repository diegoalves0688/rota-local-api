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
@Table(name = "recomendacao_atracao",
       uniqueConstraints = { @UniqueConstraint(columnNames = {"usuario_id", "atracao_id"})},
       schema = "public")
public class RecomendacaoAtracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recomendacao", nullable = false)
    private String recomendacao;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") 
    private Usuario usuario;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "atracao_id",  referencedColumnName = "id") 
    private Atracao atracao;
    
    @JsonIgnore 
    @OneToMany(mappedBy = "recomendacao", cascade = CascadeType.ALL)
    private List<AvaliacaoRecomendacao> avaliacoesRecomendacoes;

}


