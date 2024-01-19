package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.dto.AtracaoDTO;
import com.travel.rotalocal.model.entity.Atracao;

public interface AtracaoService {

    // GET
    Atracao getAtracaoById(Long atracaoId);

    List<Atracao> getAtracao(Long usuarioId, Long localizacaoId);

    List<Atracao> getUsuarioAtracoes(Long usuarioId);

    List<Atracao> getLocalizacaoAtracoes(Long localizacaoId);

    List<Atracao> getAllAtracoes();

    // SAVE
    Atracao saveAtracao(Atracao atracao, Long usuarioId, Long localizacaoId);

    // UPDATE
    Atracao updateAtracao(Long atracaoId, Atracao updatedAtracao);

    // DELETE
    void deleteAtracao(Long atracaoId);

    // RANKING
    List<AtracaoDTO> getAllAtracoesWithRanking();

    List<AtracaoDTO> search(String content);

    AtracaoDTO getAtracaoWithRankingById(Long atracaoId);

    int calculateRankingForAtracao(Atracao atracao);

    // STATE
    void ativarAtracao(Long atracaoId);

    void inativarAtracao(Long atracaoId);

}