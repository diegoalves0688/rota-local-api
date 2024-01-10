package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.model.entity.AvaliacaoRecomendacao;

public interface AvaliacaoRecomendacaoService {

    List<AvaliacaoRecomendacao> getUsuarioAvaliacaoRecomendacao(Long usuarioId);

    List<AvaliacaoRecomendacao> getRecomendacaoAvaliacaoRecomendacao(Long recomendacaoId);

    List<AvaliacaoRecomendacao> getAllAvaliacoesRecomendacao();

    AvaliacaoRecomendacao getAvaliacaoRecomendacao(Long usuarioId, Long recomendacaoId);

    AvaliacaoRecomendacao saveAvaliacaoRecomendacao(AvaliacaoRecomendacao avaliacaoRecomendacao, Long usuarioId,
            Long recomendacaoId);

    // TODO AvaliacaoRecomendacao update

    void deleteAvaliacaoRecomendacao(Long avaliacaoRecomendacaoId);

}
