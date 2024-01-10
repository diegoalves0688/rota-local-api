package com.travel.rotalocal.service;

import org.springframework.stereotype.Service;

import com.travel.rotalocal.dto.AtracaoDTO;
import com.travel.rotalocal.dto.AvaliacaoAtracaoDTO;
import com.travel.rotalocal.dto.ImagemDTO;
import com.travel.rotalocal.dto.LocalizacaoDTO;
import com.travel.rotalocal.dto.UsuarioDTO;
import com.travel.rotalocal.exception.AtracaoNotFoundException;
import com.travel.rotalocal.exception.AtracaoNotFoundException2;
import com.travel.rotalocal.exception.LocalizacaoNotFoundException;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.entity.AvaliacaoAtracao;
import com.travel.rotalocal.model.entity.CategoriaAtracao;
import com.travel.rotalocal.model.entity.Imagem;
import com.travel.rotalocal.model.entity.Localizacao;
import com.travel.rotalocal.model.entity.StatusAtracao;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.model.repository.AtracaoRepository;
import com.travel.rotalocal.model.repository.LocalizacaoRepository;
import com.travel.rotalocal.model.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AtracaoServiceImpl implements AtracaoService {

    AtracaoRepository atracaoRepository;
    LocalizacaoRepository localizacaoRepository;
    UsuarioRepository usuarioRepository;

    /**********************************
     * GET
     **********************************/
    public List<Atracao> getAtracao(Long usuarioId, Long localizacaoId) {
        List<Atracao> atracoes = atracaoRepository.findByUsuarioIdAndLocalizacaoId(usuarioId, localizacaoId);

        if (atracoes.isEmpty()) {
            throw new AtracaoNotFoundException(usuarioId, localizacaoId);
        }

        return atracoes;
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
        return (List<Atracao>) atracaoRepository.findAll();
    }

    @Override
    public List<AtracaoDTO> search(String content) {
        return convertToDTO(atracaoRepository.findByNomeContainingIgnoreCase(content));
    }

    @Override
    public Atracao getAtracaoById(Long atracaoId) {
        return atracaoRepository.findById(atracaoId).orElse(null);
    }

    /**********************************
     * POST
     **********************************/
    @Override
    public Atracao saveAtracao(Atracao atracao, Long usuarioId, Long localizacaoId) {
        Usuario usuario = UsuarioServiceImpl.unwrapUsuario(usuarioRepository.findById(usuarioId), usuarioId);
        Localizacao localizacao = LocalizacaoServiceImpl
                .unwrapLocalizacao(localizacaoRepository.findById(localizacaoId), localizacaoId);
        atracao.setUsuario(usuario);
        atracao.setLocalizacao(localizacao);
        atracao.setDataRegistro(LocalDateTime.now());
        return atracaoRepository.save(atracao);
    }

    /**********************************
     * DELETE
     **********************************/
    @Override
    public void deleteAtracao(Long atracaoId) {
        atracaoRepository.deleteById(atracaoId);
    }

    /**********************************
     * UPDATE
     **********************************/
    @Override
    public Atracao updateAtracao(Long atracaoId, Atracao updatedAtracao) {
        Atracao existingAtracao = getAtracaoById(atracaoId);

        // CAMPOS QUE NAO PODEM SER ATUALIZADOS
        updatedAtracao.setId(existingAtracao.getId());
        updatedAtracao.setUsuario(existingAtracao.getUsuario());

        // OK ALTERAR - TODO: VER COMO QUE FICARÁ COM IMAGEM
        existingAtracao.setNome(updatedAtracao.getNome());
        existingAtracao.setDescricao(updatedAtracao.getDescricao());
        existingAtracao.setAtivo(updatedAtracao.isAtivo());
        existingAtracao.setCategoria(updatedAtracao.getCategoria());
        existingAtracao.setStatus(updatedAtracao.getStatus());
        existingAtracao.setDataRegistro(LocalDateTime.now());

        Long updatedLocalizacaoId = updatedAtracao.getLocalizacao().getId();
        Localizacao updatedLocalizacao = localizacaoRepository.findById(updatedLocalizacaoId)
                .orElseThrow(() -> new LocalizacaoNotFoundException(updatedLocalizacaoId));

        existingAtracao.setLocalizacao(updatedLocalizacao);

        return atracaoRepository.save(existingAtracao);
    }

    /**********************************
     * AUXILIAR
     **********************************/
    // METODO AUXILIAR
    static Atracao unwrapAtracao(Optional<Atracao> entity, Long usuarioId, Long localizacaoId) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new AtracaoNotFoundException(usuarioId, localizacaoId);
    }

    // RANKING
    @Override
    public List<AtracaoDTO> getAllAtracoesWithRanking() {
        List<Atracao> atracoes = getAllAtracoes();
        List<AtracaoDTO> atracaoDTOList = convertToDTO(atracoes);
        return calculateRanking(atracaoDTOList);
    }

    private AtracaoDTO convertToDTO(Atracao atracao) {
        AtracaoDTO atracaoDTO = new AtracaoDTO();
        atracaoDTO.setId(atracao.getId());
        atracaoDTO.setNome(atracao.getNome());
        atracaoDTO.setDescricao(atracao.getDescricao());
        atracaoDTO.setAtivo(atracao.isAtivo());
        atracaoDTO.setStatus(StatusAtracao.valueOf(atracao.getStatus().name()));
        atracaoDTO.setDataRegistro(atracao.getDataRegistro());
        atracaoDTO.setCategoria(CategoriaAtracao.valueOf(atracao.getCategoria().name()));

        // CONVERSAO
        List<AvaliacaoAtracaoDTO> avaliacaoAtracaoDTOList = atracao.getAvaliacoesAtracoes().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        atracaoDTO.setAvaliacoesAtracoes(avaliacaoAtracaoDTOList);

        // CONVERSAO
        Usuario usuario = atracao.getUsuario();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setFoto(usuario.getFoto());
        usuarioDTO.setAtivo(usuario.isAtivo());
        usuarioDTO.setPerfil(usuario.getPerfil());
        atracaoDTO.setUsuario(usuarioDTO);

        // CONVERSAO
        Localizacao localizacao = atracao.getLocalizacao();
        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setId(localizacao.getId());
        localizacaoDTO.setPais(localizacao.getPais());
        localizacaoDTO.setEstado(localizacao.getEstado());
        localizacaoDTO.setCidade(localizacao.getCidade());
        atracaoDTO.setLocalizacao(localizacaoDTO);

        // CONVERSAO
        List<ImagemDTO> imagemDTOList = atracao.getImagens().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        atracaoDTO.setImagens(imagemDTOList);

        return atracaoDTO;
    }

    // CONVERSAO
    private AvaliacaoAtracaoDTO convertToDTO(AvaliacaoAtracao avaliacaoAtracao) {
        AvaliacaoAtracaoDTO avaliacaoAtracaoDTO = new AvaliacaoAtracaoDTO();
        avaliacaoAtracaoDTO.setId(avaliacaoAtracao.getId());
        avaliacaoAtracaoDTO.setAvaliacaoPositiva(avaliacaoAtracao.isAvaliacaoPositiva());
        return avaliacaoAtracaoDTO;
    }

    // CONVERSAO
    private ImagemDTO convertToDTO(Imagem imagem) {
        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setId(imagem.getId());
        imagemDTO.setNome(imagem.getNome());
        imagemDTO.setUrlCaminho(imagem.getUrlCaminho());

        return imagemDTO;
    }

    // CONVERSAO
    private List<AtracaoDTO> convertToDTO(List<Atracao> atracoes) {
        return atracoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // RANKING
    @Override
    public AtracaoDTO getAtracaoWithRankingById(Long atracaoId) {
        List<AtracaoDTO> atracoesWithRanking = getAllAtracoesWithRanking();

        Optional<AtracaoDTO> optionalAtracao = atracoesWithRanking.stream()
                .filter(atracaoDTO -> atracaoDTO.getId().equals(atracaoId))
                .findFirst();

        return optionalAtracao.orElseThrow(() -> new AtracaoNotFoundException2(atracaoId));
    }

    // RANKING
    private List<AtracaoDTO> calculateRanking(List<AtracaoDTO> atracaoDTOList) {
        atracaoDTOList.forEach(atracaoDTO -> {
            long trueCount = atracaoDTO.getAvaliacoesAtracoes().stream()
                    .filter(AvaliacaoAtracaoDTO::isAvaliacaoPositiva)
                    .count();

            long falseCount = atracaoDTO.getAvaliacoesAtracoes().size() - trueCount;

            atracaoDTO.setAvaliacaoSaldoPontos((int) (trueCount - falseCount));
        });

        // ORDENAR
        atracaoDTOList.sort(Comparator.comparingInt(AtracaoDTO::getAvaliacaoSaldoPontos).reversed());

        int atracaoRanking = 1;
        for (AtracaoDTO atracaoDTO : atracaoDTOList) {
            atracaoDTO.setAtracaoRanking(atracaoRanking++);
        }

        return atracaoDTOList;
    }

    // RANKING
    @Override
    public int calculateRankingForAtracao(Atracao atracao) {
        long trueCount = atracao.getAvaliacoesAtracoes().stream()
                .filter(AvaliacaoAtracao::isAvaliacaoPositiva)
                .count();

        long falseCount = atracao.getAvaliacoesAtracoes().size() - trueCount;

        return (int) (trueCount - falseCount);
    }

}
