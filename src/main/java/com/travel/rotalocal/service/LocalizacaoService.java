package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.model.entity.Localizacao;

public interface LocalizacaoService {
    
    public List<Localizacao> findAll();

    public Localizacao save(Localizacao atracao);

}
