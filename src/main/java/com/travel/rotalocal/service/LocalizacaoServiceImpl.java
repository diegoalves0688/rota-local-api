package com.travel.rotalocal.service;

import org.springframework.stereotype.Service;

import com.travel.rotalocal.exception.LocalizacaoNotFoundException;
import com.travel.rotalocal.model.entity.Localizacao;
import com.travel.rotalocal.model.repository.LocalizacaoRepository;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor 
@Service
public class LocalizacaoServiceImpl implements LocalizacaoService {

    LocalizacaoRepository localizacaoRepository;

    // metodo de auxiliar
    static Localizacao unwrapLocalizacao(Optional<Localizacao> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new LocalizacaoNotFoundException(id);
    }

    @Override
    public Localizacao getLocalizacao(Long id) {
        Optional<Localizacao> localizacao = localizacaoRepository.findById(id);
        return unwrapLocalizacao(localizacao, id);
    }

    @Override
    public Localizacao saveLocalizacao(Localizacao localizacao) {
        return localizacaoRepository.save(localizacao);
    }

    @Override
    public void deleteLocalizacao(Long id) {
        localizacaoRepository.deleteById(id);
    }

    @Override
    public List<Localizacao> getLocalizacoes() {
        return (List<Localizacao>)localizacaoRepository.findAll();
    }
}

