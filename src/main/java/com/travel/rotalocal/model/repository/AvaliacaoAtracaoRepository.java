package com.travel.rotalocal.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.travel.rotalocal.model.entity.AvaliacaoAtracao;
import jakarta.transaction.Transactional;

public interface AvaliacaoAtracaoRepository extends CrudRepository<AvaliacaoAtracao, Long> {
             
    List<AvaliacaoAtracao> findByUsuarioId(Long usuarioId);
    List<AvaliacaoAtracao> findByAtracaoId(Long atracaoId);
    Optional<AvaliacaoAtracao> findByUsuarioIdAndAtracaoId(Long usuarioId, Long atracaoId);

    @Transactional
    void deleteByUsuarioIdAndAtracaoId(Long usuarioId, Long atracaoId);

}
