package com.travel.rotalocal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travel.rotalocal.model.entity.Localizacao;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
    
}
