package com.travel.rotalocal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.rotalocal.api.dto.AtracaoDTO;
import com.travel.rotalocal.exception.AtracaoNotFoundException;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.entity.Imagem;
import com.travel.rotalocal.service.AtracaoService;
import com.travel.rotalocal.service.ImagemService;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/atracao")
public class AtracaoController {

    AtracaoService atracaoService;
    ImagemService imagemService;

    //VALIDADO POSTMAN
    @GetMapping
    public ResponseEntity getAllAtracoes() {
        List<Atracao> atracoes = atracaoService.getAllAtracoes();
        List<AtracaoDTO> resultList = new ArrayList();

        for (Atracao atracao : atracoes) {
            List<Imagem> imagemList = imagemService.getAtracaoImagens(atracao.getId());
            AtracaoDTO item = new AtracaoDTO(atracao.getId(), atracao.getNome(), atracao.getDescricao(), true,
            atracao.getCategoria(), atracao.getStatus(), atracao.getUsuario(), atracao.getLocalizacao(), imagemList);
            resultList.add(item);
        }

        return ResponseEntity.ok(resultList);
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
    
    @GetMapping("/{atracaoId}")
    public ResponseEntity getAtracaoById(@PathVariable Long atracaoId) {
        Atracao atracao = atracaoService.getAtracaoById(atracaoId);
        List<Imagem> imagemList = imagemService.getAtracaoImagens(atracao.getId());
        AtracaoDTO result = new AtracaoDTO(atracao.getId(), atracao.getNome(), atracao.getDescricao(), true,
         atracao.getCategoria(), atracao.getStatus(), atracao.getUsuario(), atracao.getLocalizacao(), imagemList);
        return ResponseEntity.ok(result);
    }

}

