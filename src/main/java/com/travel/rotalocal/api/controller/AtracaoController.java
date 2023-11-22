package com.travel.rotalocal.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.rotalocal.api.dto.HealthCheckDTO;
import com.travel.rotalocal.api.dto.HealthCheckStatus;
import com.travel.rotalocal.service.AtracaoService;

@RestController
@RequestMapping(value = "/api/atracao")
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

}
