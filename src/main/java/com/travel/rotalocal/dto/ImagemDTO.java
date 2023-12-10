package com.travel.rotalocal.dto;

public class ImagemDTO {
    
    private Long id;
    private String nome;
    private String urlCaminho;

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

    public String getUrlCaminho() {
        return this.urlCaminho;
    }

    public void setUrlCaminho(String urlCaminho) {
        this.urlCaminho = urlCaminho;
    }

}
