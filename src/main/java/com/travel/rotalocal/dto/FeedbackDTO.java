package com.travel.rotalocal.dto;

public class FeedbackDTO {
    private Long usuarioId;
    private String conteudo;

    public FeedbackDTO(Long usuarioId, String conteudo) {
        this.usuarioId = usuarioId;
        this.conteudo = conteudo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getConteudo() {
        return conteudo;
    }
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
