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
@Table(name = "localizacao", schema = "public")
public class Localizacao {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @JsonIgnore
    @OneToMany(mappedBy = "localizacao", cascade = CascadeType.ALL) 
    private List<Atracao> atracoes;

    public Localizacao(String pais, String estado, String cidade, List<Atracao> atracoes) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.atracoes = atracoes;
    }

    
}
