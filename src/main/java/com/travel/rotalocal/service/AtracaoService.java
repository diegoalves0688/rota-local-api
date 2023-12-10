package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.model.entity.Atracao;

public interface AtracaoService {
    
    Atracao getAtracaoById(Long atracaoId);

    List<Atracao> getAtracao(Long usuarioId, Long localizacaoId);

    Atracao saveAtracao(Atracao atracao, Long usuarioId, Long localizacaoId);
    //TODO - updateAtracao
    void deleteAtracao(Long usuarioId, Long localizacaoId);
    
    List<Atracao> getUsuarioAtracoes(Long usuarioId);
    List<Atracao> getLocalizacaoAtracoes(Long localizacaoId);
    List<Atracao> getAllAtracoes();
}