package com.travel.rotalocal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.rotalocal.dto.AtracaoDTO;
import com.travel.rotalocal.dto.ImagemDTO;
import com.travel.rotalocal.dto.LocalizacaoDTO;
import com.travel.rotalocal.dto.UsuarioDTO;
import com.travel.rotalocal.exception.AtracaoNotFoundException;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.entity.Imagem;
import com.travel.rotalocal.model.entity.Localizacao;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.service.AtracaoService;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/atracao")
public class AtracaoController {

    AtracaoService atracaoService;

    //VALIDADO POSTMAN
    @GetMapping
    public ResponseEntity<List<AtracaoDTO>> getAllAtracoesWithRanking() {
        try {
            List<AtracaoDTO> atracoes = atracaoService.getAllAtracoesWithRanking();
            return ResponseEntity.ok(atracoes);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    
    //VALIDADO POSTMAN
    @GetMapping("/{atracaoId}")
    public ResponseEntity<AtracaoDTO> getAtracaoWithRankingById(@PathVariable Long atracaoId) {
        try {
            AtracaoDTO atracao = atracaoService.getAtracaoWithRankingById(atracaoId);
            return ResponseEntity.ok(atracao);
        } catch (AtracaoNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
                }
        }

    //VALIDADO POSTMAN
    //BUG INVESTIGACAO
    @GetMapping("usuario/{usuarioId}/localizacao/{localizacaoId}")
    public ResponseEntity<List<Atracao>> getAtracao(
            @PathVariable Long usuarioId,
            @PathVariable Long localizacaoId
    ) {
        try {
            List<Atracao> atracoes = atracaoService.getAtracao(usuarioId, localizacaoId);
            return new ResponseEntity<>(atracoes, HttpStatus.OK);
        } catch (AtracaoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //VALIDADO POSTMAN
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Atracao>> getUsuarioAtracoes(@PathVariable Long usuarioId) {
        List<Atracao> atracoes = atracaoService.getUsuarioAtracoes(usuarioId);
        return new ResponseEntity<>(atracoes, HttpStatus.OK);
    }

    //VALIDADO POSTMAN
    @GetMapping("/localizacao/{localizacaoId}")
    public ResponseEntity<List<Atracao>> getLocalizacaoAtracoes(@PathVariable Long localizacaoId) {
        List<Atracao> atracoes = atracaoService.getLocalizacaoAtracoes(localizacaoId);
        return new ResponseEntity<>(atracoes, HttpStatus.OK);
    }

    //QUEBRADO - TO BE FIXED
    @PostMapping("usuario/{usuarioId}/localizacao/{localizacaoId}")
    public ResponseEntity<AtracaoDTO> saveAtracao(
            @RequestBody AtracaoDTO atracaoDTO,
            @PathVariable Long usuarioId,
            @PathVariable Long localizacaoId
    ) {

        Atracao atracao = converteParaAtracaoEntity(atracaoDTO);
        Atracao savedAtracao = atracaoService.saveAtracao(atracao, usuarioId, localizacaoId);

        AtracaoDTO savedAtracaoDTO = converteParaAtracaoDTO(savedAtracao);
        return new ResponseEntity<>(savedAtracaoDTO, HttpStatus.CREATED);
    }

    //TODO - REVER LOGICA DE DEL
    //VALIDADO POSTMAN - VAI DELETAR TUDO DA COMBINACAO [USUARIO + LOCALIZACAO]
    @DeleteMapping("usuario/{usuarioId}/localizacao/{localizacaoId}")
    public ResponseEntity<Void> deleteAtracao(
            @PathVariable Long usuarioId,
            @PathVariable Long localizacaoId
    ) {
        atracaoService.deleteAtracao(usuarioId, localizacaoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //METODO AUXILIAR
    private AtracaoDTO converteParaAtracaoDTO(Atracao atracao) {
        AtracaoDTO atracaoDTO = new AtracaoDTO();
        atracaoDTO.setId(atracao.getId());
        atracaoDTO.setNome(atracao.getNome());
        atracaoDTO.setDescricao(atracao.getDescricao());
        atracaoDTO.setAtivo(atracao.isAtivo());
        atracaoDTO.setCategoria(atracao.getCategoria());
        atracaoDTO.setStatus(atracao.getStatus());
        atracaoDTO.setUsuario(converteParaUsuarioDTO(atracao.getUsuario()));
        atracaoDTO.setLocalizacao(converteParaLocalizacaoDTO(atracao.getLocalizacao()));
   
        List<ImagemDTO> imagemDTOs = atracao.getImagens().stream()
                .map(this::converteParaImagemDTO)
                .collect(Collectors.toList());
        atracaoDTO.setImagens(imagemDTOs);
    
        return atracaoDTO;
    }

    //METODO AUXILIAR
    private ImagemDTO converteParaImagemDTO(Imagem imagem) {
        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setId(imagem.getId());
        imagemDTO.setNome(imagem.getNome());
        imagemDTO.setUrlCaminho(imagem.getUrlCaminho());
    
        return imagemDTO;
    }

    // METODO AUXILIAR
    private UsuarioDTO converteParaUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setFoto(usuario.getFoto());
        usuarioDTO.setAtivo(usuario.isAtivo());
        usuarioDTO.setPerfil(usuario.getPerfil());
    
        return usuarioDTO;
    }

    // METODO AUXILIAR
    private LocalizacaoDTO converteParaLocalizacaoDTO(Localizacao localizacao) {

        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setId(localizacaoDTO.getId());
        localizacaoDTO.setPais(localizacaoDTO.getPais());
        localizacaoDTO.setEstado(localizacaoDTO.getEstado());
        localizacaoDTO.setCidade(localizacaoDTO.getCidade());

        return localizacaoDTO;
    }

    // METODO AUXILIAR
    private Atracao converteParaAtracaoEntity(AtracaoDTO atracaoDTO) {
        Atracao atracao = new Atracao();
        // Map AtracaoDTO properties to Atracao entity
        atracao.setId(atracaoDTO.getId());
        atracao.setNome(atracaoDTO.getNome());
        atracao.setDescricao(atracaoDTO.getDescricao());
        atracao.setAtivo(atracaoDTO.isAtivo());
        atracao.setCategoria(atracaoDTO.getCategoria());
        atracao.setStatus(atracaoDTO.getStatus());
        atracao.setUsuario(converteParaUsuarioEntity(atracaoDTO.getUsuario()));
        atracao.setLocalizacao(converteParaLocalizacaoEntity(atracaoDTO.getLocalizacao()));
    
        return atracao;
    }

    // METODO AUXILIAR
    private Usuario converteParaUsuarioEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());

        return usuario;
    }

    // METODO AUXILIAR
    private Localizacao converteParaLocalizacaoEntity(LocalizacaoDTO localizacaoDTO) {
        Localizacao localizacao = new Localizacao();
        localizacao.setId(localizacaoDTO.getId());
        localizacao.setPais(localizacaoDTO.getPais());
        localizacao.setEstado(localizacaoDTO.getEstado());
        localizacao.setCidade(localizacaoDTO.getCidade());

        return localizacao;
    }
   
}

