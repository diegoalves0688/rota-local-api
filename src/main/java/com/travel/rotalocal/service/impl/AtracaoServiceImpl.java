package com.travel.rotalocal.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.repository.AtracaoRepository;
import com.travel.rotalocal.service.AtracaoService;

@Service
public class AtracaoServiceImpl implements AtracaoService{

    private AtracaoRepository atracaoRepository;

    public AtracaoServiceImpl(AtracaoRepository atracaoRepository) {
        this.atracaoRepository = atracaoRepository;
    }

    @Override
    public List<Atracao> findAll() {
        return atracaoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Atracao save(Atracao atracao) {
        return atracaoRepository.save(atracao);
    }
    
}
