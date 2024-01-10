package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.model.entity.AvaliacaoAtracao;

public interface AvaliacaoAtracaoService {

    AvaliacaoAtracao getAvaliacaoAtracao(Long usuarioId, Long atracaoId);

    List<AvaliacaoAtracao> getUsuarioAvaliacaoAtracao(Long usuarioId);

    List<AvaliacaoAtracao> getAtracaoAvaliacaoAtracao(Long atracaoId);

    List<AvaliacaoAtracao> getAllAvaliacoesAtracao();

    AvaliacaoAtracao saveAvaliacaoAtracao(AvaliacaoAtracao avaliacaoAtracao, Long usuarioId, Long atracaoId);

    // TODO AvaliacaoAtracao updateAvaliacaoAtracao();

    void deleteAvaliacaoAtracao(Long avaliacaoAtracaoId);

}
