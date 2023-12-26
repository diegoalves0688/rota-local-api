package com.travel.rotalocal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.rotalocal.exception.RecomendacaoAtracaoDuplicatedException;
import com.travel.rotalocal.exception.RecomendacaoAtracaoNotFoundException;
import com.travel.rotalocal.model.entity.RecomendacaoAtracao;
import com.travel.rotalocal.service.RecomendacaoAtracaoService;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/recomendacao-atracao")
public class RecomendacaoAtracaoController {

    private RecomendacaoAtracaoService recomendacaoAtracaoService;

    /**********************************
     * GET
     **********************************/
    // VALIDADO POSTMAN
    @GetMapping
    public ResponseEntity<List<RecomendacaoAtracao>> getAllRecomendacoesAtracao() {
        List<RecomendacaoAtracao> recomendacoes = recomendacaoAtracaoService.getAllRecomendacoesAtracao();
        return new ResponseEntity<>(recomendacoes, HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<RecomendacaoAtracao>> getUsuarioRecomendacaoAtracao(@PathVariable Long usuarioId) {
        List<RecomendacaoAtracao> recomendacoes = recomendacaoAtracaoService.getUsuarioRecomendacaoAtracao(usuarioId);
        return new ResponseEntity<>(recomendacoes, HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("/atracao/{atracaoId}")
    public ResponseEntity<List<RecomendacaoAtracao>> getAtracaoRecomendacaoAtracao(@PathVariable Long atracaoId) {
        List<RecomendacaoAtracao> recomendacoes = recomendacaoAtracaoService.getAtracaoRecomendacaoAtracao(atracaoId);
        return new ResponseEntity<>(recomendacoes, HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<RecomendacaoAtracao> getRecomendacaoAtracao(
            @PathVariable Long usuarioId,
            @PathVariable Long atracaoId) {
        RecomendacaoAtracao recomendacaoAtracao = recomendacaoAtracaoService.getRecomendacaoAtracao(usuarioId,
                atracaoId);
        if (recomendacaoAtracao != null) {
            return new ResponseEntity<>(recomendacaoAtracao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**********************************
     * POST
     **********************************/
    // VALIDADO POSTMAN COM TRAVA
    @PostMapping("usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<String> saveRecomendacaoAtracao(
            @RequestBody RecomendacaoAtracao recomendacaoAtracao,
            @PathVariable Long usuarioId,
            @PathVariable Long atracaoId) {
        try {
            RecomendacaoAtracao savedRecomendacaoAtracao = recomendacaoAtracaoService
                    .saveRecomendacaoAtracao(recomendacaoAtracao, usuarioId, atracaoId);
            return new ResponseEntity<>("recomendacao registrada com sucesso", HttpStatus.CREATED);
        } catch (RecomendacaoAtracaoDuplicatedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**********************************
     * DELETE
     **********************************/
    // VALIDADO POSTMAN
    @DeleteMapping("usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<Void> deleteRecomendacaoAtracao(
            @PathVariable Long usuarioId,
            @PathVariable Long atracaoId) {
        recomendacaoAtracaoService.deleteRecomendacaoAtracao(usuarioId, atracaoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**********************************
     * UPDATE
     **********************************/
    // TODO - ADD UPDATE
    @PutMapping
    public ResponseEntity<RecomendacaoAtracao> updateRecomendacaoAtracao(
            @RequestBody RecomendacaoAtracao recomendacaoAtracao) {
        try {
            RecomendacaoAtracao updatedRecomendacao = recomendacaoAtracaoService
                    .updateRecomendacaoAtracao(recomendacaoAtracao);
            return new ResponseEntity<>(updatedRecomendacao, HttpStatus.OK);
        } catch (RecomendacaoAtracaoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**********************************
     * AUXILIAR
     **********************************/

}
