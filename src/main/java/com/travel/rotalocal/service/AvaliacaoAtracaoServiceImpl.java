package com.travel.rotalocal.service;

import org.springframework.stereotype.Service;

import com.travel.rotalocal.exception.AvaliacaoAtracaoDuplicatedException;
import com.travel.rotalocal.exception.AvaliacaoAtracaoNotFoundException;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.entity.AvaliacaoAtracao;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.model.repository.AtracaoRepository;
import com.travel.rotalocal.model.repository.AvaliacaoAtracaoRepository;
import com.travel.rotalocal.model.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AvaliacaoAtracaoServiceImpl implements AvaliacaoAtracaoService {

    AvaliacaoAtracaoRepository avaliacaoAtracaoRepository;
    AtracaoRepository atracaoRepository;
    UsuarioRepository usuarioRepository;

    //METODO AUXILIAR
        static AvaliacaoAtracao unwrapAvaliacaoAtracao(Optional<AvaliacaoAtracao> entity, Long usuarioId, Long atracaoId) {
        if (entity.isPresent()) return entity.get();
        else throw new AvaliacaoAtracaoNotFoundException(usuarioId, atracaoId);
    }


    @Override
    public AvaliacaoAtracao getAvaliacaoAtracao(Long usuarioId, Long atracaoId) {
        Optional<AvaliacaoAtracao> avaliacaoAtracao = avaliacaoAtracaoRepository.findByUsuarioIdAndAtracaoId(usuarioId, atracaoId);
        return unwrapAvaliacaoAtracao(avaliacaoAtracao, usuarioId, atracaoId);
    }

    @Override
    public AvaliacaoAtracao saveAvaliacaoAtracao(AvaliacaoAtracao avaliacaoAtracao, Long usuarioId, Long atracaoId) {
        Optional<AvaliacaoAtracao> existingAvaliacao = avaliacaoAtracaoRepository.findByUsuarioIdAndAtracaoId(usuarioId, atracaoId);
            if (existingAvaliacao.isPresent()) {
            throw new AvaliacaoAtracaoDuplicatedException(usuarioId, atracaoId);

        } else {
            Usuario usuario = UsuarioServiceImpl.unwrapUsuario(usuarioRepository.findById(usuarioId), usuarioId);
            Atracao atracao = AtracaoServiceImpl.unwrapAtracao(atracaoRepository.findById(atracaoId), usuarioId, atracaoId);
            avaliacaoAtracao.setUsuario(usuario);
            avaliacaoAtracao.setAtracao(atracao);
            return avaliacaoAtracaoRepository.save(avaliacaoAtracao);
        }
    }
    
    //TODO - updateImagem

    @Override
    public void deleteAvaliacaoAtracao(Long usuarioId, Long atracaoId) {
        avaliacaoAtracaoRepository.deleteByUsuarioIdAndAtracaoId(usuarioId, atracaoId);
    }

    @Override
    public List<AvaliacaoAtracao> getUsuarioAvaliacaoAtracao(Long usuarioId) {
        return avaliacaoAtracaoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<AvaliacaoAtracao> getAtracaoAvaliacaoAtracao(Long atracaoId) {
        return avaliacaoAtracaoRepository.findByAtracaoId(atracaoId);
    }

    @Override
    public List<AvaliacaoAtracao> getAllAvaliacoesAtracao() {
        return (List<AvaliacaoAtracao>)avaliacaoAtracaoRepository.findAll();
    }
 
}

