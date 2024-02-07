package com.travel.rotalocal.dto;

public class AuthDTO {
    
    private Long userId;
    private String token;
    private String perfil;

    public AuthDTO(Long userId, String token, String perfil) {
        this.userId = userId;
        this.token = token;
        this.perfil = perfil;
    }
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getPerfil() {
        return perfil;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
