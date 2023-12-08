package com.travel.rotalocal.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "imagem",
        schema = "public")
public class Imagem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "url_caminho", nullable = false)
    private String urlCaminho;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "usuario_id", referencedColumnName = "id") 
    private Usuario usuario;

    @ManyToOne(optional = false) 
    @JoinColumn(name = "atracao_id", referencedColumnName = "id") 
    private Atracao atracao;

}