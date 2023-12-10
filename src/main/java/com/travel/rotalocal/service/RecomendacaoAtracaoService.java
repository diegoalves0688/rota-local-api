package com.travel.rotalocal.service;

import java.util.List;
import com.travel.rotalocal.model.entity.RecomendacaoAtracao;

public interface RecomendacaoAtracaoService {

    List<RecomendacaoAtracao> getUsuarioRecomendacaoAtracao(Long usuarioId);
    List<RecomendacaoAtracao> getAtracaoRecomendacaoAtracao(Long atracaoId);
    List<RecomendacaoAtracao> getAllRecomendacoesAtracao();
    RecomendacaoAtracao getRecomendacaoAtracao(Long usuarioId, Long atracaoId);

    RecomendacaoAtracao saveRecomendacaoAtracao(RecomendacaoAtracao recomendacaoAtracao, Long usuarioId, Long atracaoId);
    
    //TODO RecomendacaoAtracao updateRecomendacaoAtracao();
    
    void deleteRecomendacaoAtracao(Long usuarioId, Long atracaoId);
    
}
