package com.travel.rotalocal.service;

import org.springframework.stereotype.Service;

import com.travel.rotalocal.exception.RecomendacaoAtracaoNotFoundException;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.entity.RecomendacaoAtracao;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.model.repository.AtracaoRepository;
import com.travel.rotalocal.model.repository.RecomendacaoAtracaoRepository;
import com.travel.rotalocal.model.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecomendacaoAtracaoServiceImpl implements RecomendacaoAtracaoService {

    RecomendacaoAtracaoRepository recomendacaoAtracaoRepository;
    AtracaoRepository atracaoRepository;
    UsuarioRepository usuarioRepository;

    //metodo auxiliar
        static RecomendacaoAtracao unwrapRecomendacaoAtracao(Optional<RecomendacaoAtracao> entity, Long usuarioId, Long atracaoId) {
        if (entity.isPresent()) return entity.get();
        else throw new RecomendacaoAtracaoNotFoundException(usuarioId, atracaoId);
    }


    @Override
    public RecomendacaoAtracao getRecomendacaoAtracao(Long usuarioId, Long atracaoId) {
        Optional<RecomendacaoAtracao> recomendacaoAtracao = recomendacaoAtracaoRepository.findByUsuarioIdAndAtracaoId(usuarioId, atracaoId);
        return unwrapRecomendacaoAtracao(recomendacaoAtracao, usuarioId, atracaoId);
    }

    @Override
    public RecomendacaoAtracao saveRecomendacaoAtracao(RecomendacaoAtracao recomendacaoAtracao, Long usuarioId, Long atracaoId) {
        Usuario usuario = UsuarioServiceImpl.unwrapUsuario(usuarioRepository.findById(usuarioId), usuarioId);
        Atracao atracao = AtracaoServiceImpl.unwrapAtracao(atracaoRepository.findById(atracaoId), usuarioId, atracaoId);
        recomendacaoAtracao.setUsuario(usuario);
        recomendacaoAtracao.setAtracao(atracao);
        return recomendacaoAtracaoRepository.save(recomendacaoAtracao);

    }
    
    //TODO - update

    @Override
    public void deleteRecomendacaoAtracao(Long usuarioId, Long atracaoId) {
        recomendacaoAtracaoRepository.deleteByUsuarioIdAndAtracaoId(usuarioId, atracaoId);
    }

    @Override
    public List<RecomendacaoAtracao> getUsuarioRecomendacaoAtracao(Long usuarioId) {
        return recomendacaoAtracaoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<RecomendacaoAtracao> getAtracaoRecomendacaoAtracao(Long atracaoId) {
        return recomendacaoAtracaoRepository.findByAtracaoId(atracaoId);
    }

    @Override
    public List<RecomendacaoAtracao> getAllRecomendacoesAtracao() {
        return (List<RecomendacaoAtracao>)recomendacaoAtracaoRepository.findAll();
    }
 
}

