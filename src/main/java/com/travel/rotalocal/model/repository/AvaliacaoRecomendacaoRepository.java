package com.travel.rotalocal.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.travel.rotalocal.model.entity.AvaliacaoRecomendacao;

import jakarta.transaction.Transactional;

public interface AvaliacaoRecomendacaoRepository extends CrudRepository<AvaliacaoRecomendacao, Long> {

    List<AvaliacaoRecomendacao> findByUsuarioId(Long usuarioId);
    List<AvaliacaoRecomendacao> findByRecomendacaoId(Long recomendacaoId);  
    Optional<AvaliacaoRecomendacao> findByUsuarioIdAndRecomendacaoId(Long usuarioId, Long  recomendacaoId);

    @Transactional
    void deleteByUsuarioIdAndRecomendacaoId(Long usuarioId, Long recomendacaoId);
     
}
