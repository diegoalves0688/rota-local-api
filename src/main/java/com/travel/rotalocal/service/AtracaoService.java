package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.model.entity.Atracao;

public interface AtracaoService {
    
    public List<Atracao> findAll();

    public Atracao findById(Long id);

    public Atracao save(Atracao atracao);

}
