package com.travel.rotalocal.dto;

public class AvaliacaoAtracaoDTO {

    private Long id;
    private boolean avaliacaoPositiva;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAvaliacaoPositiva() {
        return this.avaliacaoPositiva;
    }

    public boolean getAvaliacaoPositiva() {
        return this.avaliacaoPositiva;
    }

    public void setAvaliacaoPositiva(boolean avaliacaoPositiva) {
        this.avaliacaoPositiva = avaliacaoPositiva;
    }

}
