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
import com.travel.rotalocal.model.entity.CategoriaAtracao;
import com.travel.rotalocal.model.entity.Imagem;
import com.travel.rotalocal.model.entity.Localizacao;
import com.travel.rotalocal.model.entity.StatusAtracao;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.service.AtracaoService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/atracao")
public class AtracaoController {

    AtracaoService atracaoService;

    /**********************************
     * GET
     **********************************/
    // VALIDADO POSTMAN
    @GetMapping
    public ResponseEntity<List<AtracaoDTO>> getAllAtracoesWithRanking(
            @RequestParam(required = false) CategoriaAtracao categoria) {
        try {
            List<AtracaoDTO> atracoes = atracaoService.getAllAtracoesWithRanking();
            if (categoria != null) {
                atracoes = atracoes.stream()
                        .filter(atracao -> categoria.equals(atracao.getCategoria()))
                        .collect(Collectors.toList());
            }
            atracoes.sort(Comparator.comparing(AtracaoDTO::getAtracaoRanking));
            return ResponseEntity.ok(atracoes);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    // VALIDADO POSTMAN
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

    // VALIDADO POSTMAN
    @GetMapping("usuario/{usuarioId}/localizacao/{localizacaoId}")
    public ResponseEntity<List<Atracao>> getAtracao(
            @PathVariable Long usuarioId,
            @PathVariable Long localizacaoId) {
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

    // VALIDADO POSTMAN
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Atracao>> getUsuarioAtracoes(@PathVariable Long usuarioId) {
        List<Atracao> atracoes = atracaoService.getUsuarioAtracoes(usuarioId);
        return new ResponseEntity<>(atracoes, HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("/localizacao/{localizacaoId}")
    public ResponseEntity<List<Atracao>> getLocalizacaoAtracoes(@PathVariable Long localizacaoId) {
        List<Atracao> atracoes = atracaoService.getLocalizacaoAtracoes(localizacaoId);
        return new ResponseEntity<>(atracoes, HttpStatus.OK);
    }

    /**********************************
     * POST
     **********************************/
    // VALIDADO POSTMAN 20231223 - TODO add trava
    @PostMapping
    public ResponseEntity novaAtracao(@RequestBody AtracaoDTO atracaoDTO) {

        Atracao atracao = new Atracao();
        atracao.setNome(atracaoDTO.getNome());
        atracao.setDescricao(atracaoDTO.getDescricao());
        atracao.setAtivo(true);
        atracao.setCategoria(atracaoDTO.getCategoria());
        atracao.setStatus(StatusAtracao.PUBLICO);
        atracao.setDataRegistro(LocalDateTime.now());

        return ResponseEntity.ok(atracaoService.saveAtracao(atracao, atracaoDTO.getUsuario().getId(),
                atracaoDTO.getLocalizacao().getId()));
    }

    // QUEBRADO - TO BE FIXED
    @PostMapping("usuario/{usuarioId}/localizacao/{localizacaoId}")
    public ResponseEntity<AtracaoDTO> saveAtracao(
            @RequestBody AtracaoDTO atracaoDTO,
            @PathVariable Long usuarioId,
            @PathVariable Long localizacaoId) {

        System.out.println("Usuario: " + atracaoDTO.getUsuario());
        System.out.println("Localizacao: " + atracaoDTO.getLocalizacao());

        if (!usuarioId.equals(atracaoDTO.getUsuario().getId())
                || !localizacaoId.equals(atracaoDTO.getLocalizacao().getId())) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            Atracao atracao = converteParaAtracaoEntity(atracaoDTO);
            Atracao savedAtracao = atracaoService.saveAtracao(atracao, usuarioId, localizacaoId);

            AtracaoDTO savedAtracaoDTO = converteParaAtracaoDTO(savedAtracao);
            return new ResponseEntity<>(savedAtracaoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**********************************
     * DELETE
     **********************************/
    // TODO - REVER LOGICA DE DEL
    // VALIDADO POSTMAN - VAI DELETAR TUDO DA COMBINACAO [USUARIO + LOCALIZACAO]
    @DeleteMapping("usuario/{usuarioId}/localizacao/{localizacaoId}")
    public ResponseEntity<Void> deleteAtracao(
            @PathVariable Long usuarioId,
            @PathVariable Long localizacaoId) {
        atracaoService.deleteAtracao(usuarioId, localizacaoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**********************************
     * UPDATE
     **********************************/
    // VALIDADO POSTMAN
    @PutMapping("/{atracaoId}")
    public ResponseEntity<Atracao> updateAtracao(
            @PathVariable Long atracaoId,
            @RequestBody Atracao updatedAtracao) {
        try {
            Atracao updatedAtracaoResult = atracaoService.updateAtracao(atracaoId, updatedAtracao);
            return ResponseEntity.ok(updatedAtracaoResult);
        } catch (AtracaoNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**********************************
     * AUXILIAR
     **********************************/
    // METODO AUXILIAR
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

    // METODO AUXILIAR
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
