package com.travel.rotalocal.service;

import org.springframework.stereotype.Service;

import com.travel.rotalocal.exception.RecomendacaoAtracaoDuplicatedException;
import com.travel.rotalocal.exception.RecomendacaoAtracaoNotFoundException;
import com.travel.rotalocal.exception.RecomendacaoAtracaoNotFoundException2;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.entity.RecomendacaoAtracao;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.model.repository.AtracaoRepository;
import com.travel.rotalocal.model.repository.RecomendacaoAtracaoRepository;
import com.travel.rotalocal.model.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecomendacaoAtracaoServiceImpl implements RecomendacaoAtracaoService {

    RecomendacaoAtracaoRepository recomendacaoAtracaoRepository;
    AtracaoRepository atracaoRepository;
    UsuarioRepository usuarioRepository;

    /**********************************
     * GET
     **********************************/
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
        return (List<RecomendacaoAtracao>) recomendacaoAtracaoRepository.findAll();
    }

    @Override
    public RecomendacaoAtracao getRecomendacaoAtracao(Long usuarioId, Long atracaoId) {
        Optional<RecomendacaoAtracao> recomendacaoAtracao = recomendacaoAtracaoRepository
                .findByUsuarioIdAndAtracaoId(usuarioId, atracaoId);
        return unwrapRecomendacaoAtracao(recomendacaoAtracao, usuarioId, atracaoId);
    }

    /**********************************
     * SAVE
     **********************************/
    @Override
    public RecomendacaoAtracao saveRecomendacaoAtracao(RecomendacaoAtracao recomendacaoAtracao, Long usuarioId,
            Long atracaoId) {
        Optional<RecomendacaoAtracao> existingRecomendacao = recomendacaoAtracaoRepository
                .findByUsuarioIdAndAtracaoId(usuarioId, atracaoId);
        if (existingRecomendacao.isPresent()) {
            throw new RecomendacaoAtracaoDuplicatedException(usuarioId, atracaoId);

        } else {
            Usuario usuario = UsuarioServiceImpl.unwrapUsuario(usuarioRepository.findById(usuarioId), usuarioId);
            Atracao atracao = AtracaoServiceImpl.unwrapAtracao(atracaoRepository.findById(atracaoId), usuarioId,
                    atracaoId);
            recomendacaoAtracao.setUsuario(usuario);
            recomendacaoAtracao.setAtracao(atracao);
            return recomendacaoAtracaoRepository.save(recomendacaoAtracao);
        }
    }

    /**********************************
     * DELETE
     **********************************/
    @Override
    public void deleteRecomendacaoAtracao(Long recomendacaoAtracaoId) {
        recomendacaoAtracaoRepository.deleteById(recomendacaoAtracaoId);
    }

    /**********************************
     * UPDATE
     **********************************/
    @Override
    public RecomendacaoAtracao updateRecomendacaoAtracao(RecomendacaoAtracao recomendacaoAtracao) {
        if (recomendacaoAtracao.getId() == null) {
            throw new IllegalArgumentException("Recomendacao Atracao nao pode ter id nulo");
        }

        RecomendacaoAtracao existingRecomendacao = recomendacaoAtracaoRepository.findById(recomendacaoAtracao.getId())
                .orElseThrow(() -> new RecomendacaoAtracaoNotFoundException2(recomendacaoAtracao.getId()));

        if (recomendacaoAtracao.getRecomendacao() != null) {
            existingRecomendacao.setRecomendacao(recomendacaoAtracao.getRecomendacao());
            existingRecomendacao.setDataRegistro(LocalDateTime.now());
        }

        return recomendacaoAtracaoRepository.save(existingRecomendacao);
    }

    /**********************************
     * AUXILIAR
     **********************************/
    // METODO AUXILIAR
    static RecomendacaoAtracao unwrapRecomendacaoAtracao(Optional<RecomendacaoAtracao> entity, Long usuarioId,
            Long atracaoId) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new RecomendacaoAtracaoNotFoundException(usuarioId, atracaoId);
    }

}
