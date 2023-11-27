package com.travel.rotalocal.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.rotalocal.api.dto.LocalizacaoDTO;
import com.travel.rotalocal.model.entity.Localizacao;
import com.travel.rotalocal.service.LocalizacaoService;

@RestController
@RequestMapping(value = "/api/localizacoes")
@CrossOrigin(origins = "*")
public class LocalizacaoController {

    LocalizacaoService localizacaoService;

    public LocalizacaoController(LocalizacaoService localizacaoService) {
        this.localizacaoService = localizacaoService;
    }
    
    @GetMapping
    public ResponseEntity listarLocalizacoes(){
        return ResponseEntity.ok(localizacaoService.findAll());
    }
    // confirmar como ficara o post para localizacao
    @PostMapping
    public ResponseEntity criarLocalizacao(@RequestBody LocalizacaoDTO localizacaoDTO){
        try{
            Localizacao localizacao = new Localizacao();
            localizacao.setPais(localizacaoDTO.getPais());
            localizacao.setEstado(localizacaoDTO.getEstado());
            localizacao.setCidade(localizacaoDTO.getCidade());
            return ResponseEntity.ok(localizacaoService.save(localizacao));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
