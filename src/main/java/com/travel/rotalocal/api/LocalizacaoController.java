package com.travel.rotalocal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.rotalocal.model.entity.Localizacao;
import com.travel.rotalocal.service.LocalizacaoService;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/localizacao")
public class LocalizacaoController {

    LocalizacaoService localizacaoService;

    // VALIDADO POSTMAN
    @GetMapping
    public ResponseEntity<List<Localizacao>> getLocalizacao() {
        return new ResponseEntity<>(localizacaoService.getLocalizacoes(), HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("/{id}")
    public ResponseEntity<Localizacao> getLocalizacao(@PathVariable Long id) {
        return new ResponseEntity<>(localizacaoService.getLocalizacao(id), HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @PostMapping
    public ResponseEntity<Localizacao> saveLocalizacao(@RequestBody Localizacao localizacao) {
        return new ResponseEntity<>(localizacaoService.saveLocalizacao(localizacao), HttpStatus.CREATED);
    }
    
    // VALIDADO POSTMAN
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLocalizacao(@PathVariable Long id) {
        localizacaoService.deleteLocalizacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}