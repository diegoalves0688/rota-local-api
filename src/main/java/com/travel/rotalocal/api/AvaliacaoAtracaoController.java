package com.travel.rotalocal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.rotalocal.model.entity.AvaliacaoAtracao;
import com.travel.rotalocal.service.AvaliacaoAtracaoService;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/avaliacao-atracao")
public class AvaliacaoAtracaoController {
    
    private AvaliacaoAtracaoService avaliacaoAtracaoService;

    //VALIDADO POSTMAN
    @GetMapping
    public ResponseEntity<List<AvaliacaoAtracao>> getAllAvaliacoesAtracao() {
        List<AvaliacaoAtracao> avaliacoes = avaliacaoAtracaoService.getAllAvaliacoesAtracao();
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    //VALIDADO POSTMAN
    @GetMapping("usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<AvaliacaoAtracao> getAvaliacaoAtracao(
            @PathVariable Long usuarioId,
            @PathVariable Long atracaoId
    ) {
        AvaliacaoAtracao avaliacaoAtracao = avaliacaoAtracaoService.getAvaliacaoAtracao(usuarioId, atracaoId);
        return new ResponseEntity<>(avaliacaoAtracao, HttpStatus.OK);
    }

    //VALIDADO POSTMAN
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AvaliacaoAtracao>> getUsuarioAvaliacaoAtracao(@PathVariable Long usuarioId) {
        List<AvaliacaoAtracao> avaliacoes = avaliacaoAtracaoService.getUsuarioAvaliacaoAtracao(usuarioId);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    //VALIDADO POSTMAN
    @GetMapping("/atracao/{atracaoId}")
    public ResponseEntity<List<AvaliacaoAtracao>> getAtracaoAvaliacaoAtracao(@PathVariable Long atracaoId) {
        List<AvaliacaoAtracao> avaliacoes = avaliacaoAtracaoService.getAtracaoAvaliacaoAtracao(atracaoId);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    //VALIDADO POSTMAN
    @PostMapping("usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<AvaliacaoAtracao> saveAvaliacaoAtracao(
            @RequestBody AvaliacaoAtracao avaliacaoAtracao,
            @PathVariable Long usuarioId,
            @PathVariable Long atracaoId
    ) {
        AvaliacaoAtracao savedAvaliacao = avaliacaoAtracaoService.saveAvaliacaoAtracao(avaliacaoAtracao, usuarioId, atracaoId);
        return new ResponseEntity<>(savedAvaliacao, HttpStatus.CREATED);
    }
    
    //VALIDADO POSTMAN
    @DeleteMapping("usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<Void> deleteAvaliacaoAtracao(
            @PathVariable Long usuarioId,
            @PathVariable Long atracaoId
    ) {
        avaliacaoAtracaoService.deleteAvaliacaoAtracao(usuarioId, atracaoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


