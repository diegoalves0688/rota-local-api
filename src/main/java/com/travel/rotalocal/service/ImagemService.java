package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.model.entity.Imagem;

public interface ImagemService {

    List<Imagem> getUsuarioImagens(Long usuarioId);
    List<Imagem> getAtracaoImagens(Long atracaoId);
    List<Imagem> getAllImagens();
    Imagem getImagem(Long imagemId);
    List<Imagem> getImagens(Long usuarioId, Long atracaoId); 
    
    List<Imagem> saveImagens(List<Imagem> imagens, Long usuarioId, Long atracaoId);

    //TODO - updateImagem

    void deleteImagem(Long usuarioId, Long atracaoId);

}

