package com.travel.rotalocal.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.rotalocal.api.dto.AtracaoDTO;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.entity.Categoria;
import com.travel.rotalocal.service.AtracaoService;

@RestController
@RequestMapping(value = "/api/atracoes")
@CrossOrigin(origins = "*")
public class AtracaoController {

    AtracaoService atracaoService;

    public AtracaoController(AtracaoService atracaoService) {
        this.atracaoService = atracaoService;
    }
    
    @GetMapping
    public ResponseEntity listarAtracoes(){
        return ResponseEntity.ok(atracaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAtracao(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(atracaoService.findById(id));
    }

    @PostMapping
    public ResponseEntity criarAtracao(@RequestBody AtracaoDTO atracaoDTO){
        try{
            Atracao atracao = new Atracao();
            atracao.setNome(atracaoDTO.getNome());
            atracao.setCategoria(Categoria.valueOf(atracaoDTO.getCategoria()));
            atracao.setDescricao(atracaoDTO.getDescricao());
            atracao.setFoto(atracaoDTO.getFoto());
            atracao.setLocalizacaoId(1L);
            atracao.setUsuarioId(1L);
            return ResponseEntity.ok(atracaoService.save(atracao));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
