package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.model.entity.Imagem;

public interface ImagemService {

    List<Imagem> getImagens(Long usuarioId, Long atracaoId); 
    
    Imagem saveImagem(Imagem imagem, Long usuarioId, Long atracaoId);
    //TODO - updateImagem
    //Imagem updateImagem();
    void deleteImagem(Long usuarioId, Long atracaoId);
    
    List<Imagem> getUsuarioImagens(Long usuarioId);
    List<Imagem> getAtracaoImagens(Long atracaoId);
    List<Imagem> getAllImagens();
}

