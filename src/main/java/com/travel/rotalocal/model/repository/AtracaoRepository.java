package com.travel.rotalocal.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.travel.rotalocal.model.entity.Atracao;
import jakarta.transaction.Transactional;

public interface AtracaoRepository extends CrudRepository<Atracao, Long> {

    Optional<Atracao> findById(Long atracaoId);

    List<Atracao> findByUsuarioId(Long usuarioId);

    List<Atracao> findByLocalizacaoId(Long localizacaoId);

    List<Atracao> findByUsuarioIdAndLocalizacaoId(Long usuarioId, Long localizacaoId);

    List<Atracao> findByNomeContainingIgnoreCase(String content);

    @Transactional
    void deleteByUsuarioIdAndLocalizacaoId(Long usuarioId, Long localizacaoId);

}