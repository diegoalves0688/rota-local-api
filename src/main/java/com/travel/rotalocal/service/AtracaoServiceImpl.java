package com.travel.rotalocal.service;

import org.springframework.stereotype.Service;

import com.travel.rotalocal.exception.AtracaoNotFoundException;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.entity.Localizacao;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.model.repository.AtracaoRepository;
import com.travel.rotalocal.model.repository.LocalizacaoRepository;
import com.travel.rotalocal.model.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AtracaoServiceImpl implements AtracaoService {

    AtracaoRepository atracaoRepository;
    LocalizacaoRepository localizacaoRepository;
    UsuarioRepository usuarioRepository;

    //metodo auxiliar
        static Atracao unwrapAtracao(Optional<Atracao> entity, Long usuarioId, Long localizacaoId) {
        if (entity.isPresent()) return entity.get();
        else throw new AtracaoNotFoundException(usuarioId, localizacaoId);
    }

    public List<Atracao> getAtracao(Long usuarioId, Long localizacaoId) {
        List<Atracao> atracoes = atracaoRepository.findByUsuarioIdAndLocalizacaoId(usuarioId, localizacaoId);
    
        if (atracoes.isEmpty()) {
            throw new AtracaoNotFoundException(usuarioId, localizacaoId);
        }
    
        return atracoes;
    }
    
    @Override
    public Atracao saveAtracao(Atracao atracao, Long usuarioId, Long localizacaoId) {
        Usuario usuario = UsuarioServiceImpl.unwrapUsuario(usuarioRepository.findById(usuarioId), usuarioId);
        Localizacao localizacao = LocalizacaoServiceImpl.unwrapLocalizacao(localizacaoRepository.findById(localizacaoId), localizacaoId);
        atracao.setUsuario(usuario);
        atracao.setLocalizacao(localizacao);
        return atracaoRepository.save(atracao);
    }
    
    //TODO - updateAtracao
   
    @Override
    public void deleteAtracao(Long usuarioId, Long localizacaoId) {
        atracaoRepository.deleteByUsuarioIdAndLocalizacaoId(usuarioId, localizacaoId);
    }

    @Override
    public List<Atracao> getUsuarioAtracoes(Long usuarioId) {
        return atracaoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Atracao> getLocalizacaoAtracoes(Long localizacaoId) {
        return atracaoRepository.findByLocalizacaoId(localizacaoId);
    }

    @Override
    public List<Atracao> getAllAtracoes() {
        return (List<Atracao>)atracaoRepository.findAll();
    }

    @Override
    public Atracao getAtracaoById(Long atracaoId) {
        return atracaoRepository.findById(atracaoId).get();
    }
}

