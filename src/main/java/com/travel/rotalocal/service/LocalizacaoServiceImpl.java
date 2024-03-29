package com.travel.rotalocal.service;

import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import com.travel.rotalocal.exception.LocalizacaoNotFoundException;
import com.travel.rotalocal.model.entity.Localizacao;
import com.travel.rotalocal.model.repository.LocalizacaoRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor 
@Service
public class LocalizacaoServiceImpl implements LocalizacaoService {

    LocalizacaoRepository localizacaoRepository;

    // METODO AUXILAR
    static Localizacao unwrapLocalizacao(Optional<Localizacao> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new LocalizacaoNotFoundException(id);
    }

    @Override
    public Localizacao getLocalizacao(Long id) {
        Optional<Localizacao> localizacao = localizacaoRepository.findById(id);
        return unwrapLocalizacao(localizacao, id);
    }

    @Transactional // regra de negocio
    public Localizacao saveLocalizacao(Localizacao localizacao) {
        Optional<Localizacao> existingLocalizacao = localizacaoRepository
                .findByPaisAndEstadoAndCidade(localizacao.getPais(), localizacao.getEstado(), localizacao.getCidade());
        if (existingLocalizacao.isPresent()) {
            throw new DataIntegrityViolationException("esta localizacao ja existe....");
        }
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

    @Override
    public Localizacao getLocalizacaoByPaisEstadoCidade(String pais, String estado, String cidade) {
        Optional<Localizacao> existingLocalizacao = localizacaoRepository
                .findByPaisAndEstadoAndCidade(pais, estado, cidade);
        return existingLocalizacao.isPresent() ? existingLocalizacao.get() : null;
    }

    
}

