package com.travel.rotalocal.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.travel.rotalocal.model.entity.Localizacao;
import com.travel.rotalocal.model.repository.LocalizacaoRepository;
import com.travel.rotalocal.service.LocalizacaoService;

@Service
public class LocalizacaoServiceImpl implements LocalizacaoService{

    private LocalizacaoRepository localizacaoRepository;

    public LocalizacaoServiceImpl(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    @Override
    public List<Localizacao> findAll() {
        return localizacaoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Localizacao save(Localizacao atracao) {
        return localizacaoRepository.save(atracao);
    }
    
}
