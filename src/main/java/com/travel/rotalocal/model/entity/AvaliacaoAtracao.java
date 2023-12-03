package com.travel.rotalocal.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "avaliacao_atracao",
        uniqueConstraints = { @UniqueConstraint(columnNames = {"usuario_id", "atracao_id"})},
        schema = "public")
public class AvaliacaoAtracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "avaliacao_positiva", nullable = false)
    private boolean avaliacaoPositiva;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") 
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "atracao_id", referencedColumnName = "id") 
    private Atracao atracao;


}