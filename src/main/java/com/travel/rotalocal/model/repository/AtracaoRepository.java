package com.travel.rotalocal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.rotalocal.model.entity.Atracao;

public interface AtracaoRepository extends JpaRepository<Atracao, Long> {
    
}
