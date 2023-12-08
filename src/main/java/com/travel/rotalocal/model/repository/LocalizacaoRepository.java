package com.travel.rotalocal.model.repository;

import com.travel.rotalocal.model.entity.Localizacao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface LocalizacaoRepository extends CrudRepository<Localizacao, Long> {
    
    //regra negocio
    Optional<Localizacao> findByPaisAndEstadoAndCidade(String pais, String estado, String cidade);
}
