package com.travel.rotalocal.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "avaliacao_recomendacao",
        uniqueConstraints = { @UniqueConstraint(columnNames = {"usuario_id", "recomendacao_id"})},
        schema = "public")
public class AvaliacaoRecomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "avaliacao_positiva", nullable = false)
    private boolean avaliacaoPositiva;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") 
    private Usuario usuario;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "recomendacao_id",  referencedColumnName = "id") 
    private RecomendacaoAtracao recomendacao;
    
}
