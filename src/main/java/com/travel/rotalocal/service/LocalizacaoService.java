package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.model.entity.Localizacao;

public interface LocalizacaoService {

    Localizacao getLocalizacao(Long id);
    void saveLocalizacao(Localizacao localizacao); //regra de negocio
    void deleteLocalizacao(Long id);
    List<Localizacao> getLocalizacoes();

}
