package com.travel.rotalocal.service;

import org.springframework.stereotype.Service;

import com.travel.rotalocal.exception.AvaliacaoRecomendacaoDuplicatedException;
import com.travel.rotalocal.exception.AvaliacaoRecomendacaoNotFoundException;
import com.travel.rotalocal.model.entity.AvaliacaoRecomendacao;
import com.travel.rotalocal.model.entity.RecomendacaoAtracao;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.model.repository.AvaliacaoRecomendacaoRepository;
import com.travel.rotalocal.model.repository.RecomendacaoAtracaoRepository;
import com.travel.rotalocal.model.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AvaliacaoRecomendacaoServiceImpl implements AvaliacaoRecomendacaoService {

    AvaliacaoRecomendacaoRepository avaliacaoRecomendacaoRepository;
    RecomendacaoAtracaoRepository recomendacaoAtracaoRepository;
    UsuarioRepository usuarioRepository;

    // METODO AUXILIAR
    static AvaliacaoRecomendacao unwrapAvaliacaoRecomendacao(Optional<AvaliacaoRecomendacao> entity, Long usuarioId,
            Long atracaoId) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new AvaliacaoRecomendacaoNotFoundException(usuarioId, atracaoId);
    }

    @Override
    public AvaliacaoRecomendacao getAvaliacaoRecomendacao(Long usuarioId, Long recomendacaoId) {
        Optional<AvaliacaoRecomendacao> avaliacaoRecomendacao = avaliacaoRecomendacaoRepository
                .findByUsuarioIdAndRecomendacaoId(usuarioId, recomendacaoId);
        return unwrapAvaliacaoRecomendacao(avaliacaoRecomendacao, usuarioId, recomendacaoId);
    }

    @Override
    public List<AvaliacaoRecomendacao> getUsuarioAvaliacaoRecomendacao(Long usuarioId) {
        return avaliacaoRecomendacaoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<AvaliacaoRecomendacao> getRecomendacaoAvaliacaoRecomendacao(Long recomendacaoId) {
        return avaliacaoRecomendacaoRepository.findByRecomendacaoId(recomendacaoId);
    }

    @Override
    public List<AvaliacaoRecomendacao> getAllAvaliacoesRecomendacao() {
        return (List<AvaliacaoRecomendacao>) avaliacaoRecomendacaoRepository.findAll();
    }

    @Override
    public AvaliacaoRecomendacao saveAvaliacaoRecomendacao(AvaliacaoRecomendacao avaliacaoRecomendacao, Long usuarioId,
            Long recomendacaoId) {
        Optional<AvaliacaoRecomendacao> existingAvaliacao = avaliacaoRecomendacaoRepository
                .findByUsuarioIdAndRecomendacaoId(usuarioId, recomendacaoId);
        if (existingAvaliacao.isPresent()) {
            throw new AvaliacaoRecomendacaoDuplicatedException(usuarioId, recomendacaoId);

        } else {
            Usuario usuario = UsuarioServiceImpl.unwrapUsuario(usuarioRepository.findById(usuarioId), usuarioId);
            RecomendacaoAtracao recomendacao = RecomendacaoAtracaoServiceImpl.unwrapRecomendacaoAtracao(
                    recomendacaoAtracaoRepository.findById(recomendacaoId), usuarioId, recomendacaoId);
            avaliacaoRecomendacao.setUsuario(usuario);
            avaliacaoRecomendacao.setRecomendacao(recomendacao);
            return avaliacaoRecomendacaoRepository.save(avaliacaoRecomendacao);
        }
    }

    // TODO - update

    @Override
    public void deleteAvaliacaoRecomendacao(Long avaliacaoRecomendacaoId) {
        avaliacaoRecomendacaoRepository.deleteById(avaliacaoRecomendacaoId);
    }

}
