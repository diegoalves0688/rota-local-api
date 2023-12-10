package com.travel.rotalocal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.rotalocal.dto.AtracaoDTO;
import com.travel.rotalocal.dto.ImagemDTO;
import com.travel.rotalocal.exception.AtracaoNotFoundException;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.entity.Imagem;
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
    public ResponseEntity<List<AtracaoDTO>> getAllAtracoesWithDetails() {
        List<Atracao> atracoes = atracaoService.getAllAtracoes();
        List<AtracaoDTO> atracoesWithDetails = atracoes.stream()
                .map(this::converteParaAtracaoDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(atracoesWithDetails, HttpStatus.OK);
    }

    //VALIDADO POSTMAN
    @GetMapping("/{atracaoId}")
    public ResponseEntity<AtracaoDTO> getAtracaoById(@PathVariable Long atracaoId) {
        try {
            Atracao atracao = atracaoService.getAtracaoById(atracaoId);

            if (atracao != null) {
                AtracaoDTO atracaoDTO = converteParaAtracaoDTO(atracao);
                return new ResponseEntity<>(atracaoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

    //VALIDADO POSTMAN
    @PostMapping("usuario/{usuarioId}/localizacao/{localizacaoId}")
    public ResponseEntity<Atracao> saveAtracao(
            @RequestBody Atracao atracao,
            @PathVariable Long usuarioId,
            @PathVariable Long localizacaoId
    ) {
        Atracao savedAtracao = atracaoService.saveAtracao(atracao, usuarioId, localizacaoId);
        return new ResponseEntity<>(savedAtracao, HttpStatus.CREATED);
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
        atracaoDTO.setUsuario(atracao.getUsuario());
        atracaoDTO.setLocalizacao(atracao.getLocalizacao());
    
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
    
}

