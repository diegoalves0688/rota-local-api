package com.travel.rotalocal.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.travel.rotalocal.model.entity.RecomendacaoAtracao;
import jakarta.transaction.Transactional;

public interface RecomendacaoAtracaoRepository extends CrudRepository<RecomendacaoAtracao, Long> {

    Optional<RecomendacaoAtracao> findByUsuarioIdAndAtracaoId(Long usuarioId, Long atracaoId);

    @Transactional
    void deleteByUsuarioIdAndAtracaoId(Long usuarioId, Long atracaoId);
         
    List<RecomendacaoAtracao> findByUsuarioId(Long usuarioId);
    List<RecomendacaoAtracao> findByAtracaoId(Long atracaoId);
    
}
