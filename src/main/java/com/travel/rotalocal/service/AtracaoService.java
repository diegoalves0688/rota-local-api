package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.dto.AtracaoDTO;
import com.travel.rotalocal.model.entity.Atracao;

public interface AtracaoService {

    Atracao getAtracaoById(Long atracaoId);
    List<Atracao> getAtracao(Long usuarioId, Long localizacaoId);
    List<Atracao> getUsuarioAtracoes(Long usuarioId);
    List<Atracao> getLocalizacaoAtracoes(Long localizacaoId);
    List<Atracao> getAllAtracoes();

    Atracao saveAtracao(Atracao atracao, Long usuarioId, Long localizacaoId);
    
    //TODO - updateAtracao
    
    void deleteAtracao(Long usuarioId, Long localizacaoId);
    
    //RANKING
    List<AtracaoDTO> getAllAtracoesWithRanking();
    AtracaoDTO getAtracaoWithRankingById(Long atracaoId);
    int calculateRankingForAtracao(Atracao atracao);

}