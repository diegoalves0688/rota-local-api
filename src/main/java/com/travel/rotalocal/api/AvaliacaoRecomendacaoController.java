package com.travel.rotalocal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.rotalocal.exception.AvaliacaoRecomendacaoDuplicatedException;
import com.travel.rotalocal.model.entity.AvaliacaoRecomendacao;
import com.travel.rotalocal.service.AvaliacaoRecomendacaoService;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/avaliacao-recomendacao")
public class AvaliacaoRecomendacaoController {

    private AvaliacaoRecomendacaoService avaliacaoRecomendacaoService;

    /**********************************
     * GET
     **********************************/
    // VALIDADO POSTMAN
    @GetMapping
    public ResponseEntity<List<AvaliacaoRecomendacao>> getAllAvaliacoesRecomendacao() {
        List<AvaliacaoRecomendacao> avaliacoes = avaliacaoRecomendacaoService.getAllAvaliacoesRecomendacao();
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AvaliacaoRecomendacao>> getUsuarioAvaliacaoRecomendacao(@PathVariable Long usuarioId) {
        List<AvaliacaoRecomendacao> avaliacoes = avaliacaoRecomendacaoService
                .getUsuarioAvaliacaoRecomendacao(usuarioId);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("/recomendacao/{recomendacaoId}")
    public ResponseEntity<List<AvaliacaoRecomendacao>> getRecomendacaoAvaliacaoRecomendacao(
            @PathVariable Long recomendacaoId) {
        List<AvaliacaoRecomendacao> avaliacoes = avaliacaoRecomendacaoService
                .getRecomendacaoAvaliacaoRecomendacao(recomendacaoId);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("usuario/{usuarioId}/recomendacao/{recomendacaoId}")
    public ResponseEntity<AvaliacaoRecomendacao> getAvaliacaoRecomendacao(
            @PathVariable Long usuarioId,
            @PathVariable Long recomendacaoId) {
        AvaliacaoRecomendacao avaliacaoRecomendacao = avaliacaoRecomendacaoService.getAvaliacaoRecomendacao(usuarioId,
                recomendacaoId);
        return new ResponseEntity<>(avaliacaoRecomendacao, HttpStatus.OK);
    }

    /**********************************
     * POST
     **********************************/
    // VALIDADO POSTMAN COM TRAVA
    @PostMapping("usuario/{usuarioId}/recomendacao/{recomendacaoId}")
    public ResponseEntity<String> saveAvaliacaoRecomendacao(
            @RequestBody AvaliacaoRecomendacao avaliacaoRecomendacao,
            @PathVariable Long usuarioId,
            @PathVariable Long recomendacaoId) {
        try {
            AvaliacaoRecomendacao savedAvaliacao = avaliacaoRecomendacaoService
                    .saveAvaliacaoRecomendacao(avaliacaoRecomendacao, usuarioId, recomendacaoId);
            return new ResponseEntity<>("avaliacao de recomencadacao registrada com sucesso", HttpStatus.OK);
        } catch (AvaliacaoRecomendacaoDuplicatedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**********************************
     * DELETE
     **********************************/
    // VALIDADO POSTMAN
    @DeleteMapping("usuario/{usuarioId}/recomendacao/{recomendacaoId}")
    public ResponseEntity<Void> deleteAvaliacaoRecomendacao(
            @PathVariable Long usuarioId,
            @PathVariable Long recomendacaoId) {
        avaliacaoRecomendacaoService.deleteAvaliacaoRecomendacao(usuarioId, recomendacaoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**********************************
     * UPDATE - TO DO
     **********************************/
}
