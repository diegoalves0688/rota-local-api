package com.travel.rotalocal.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.service.UsuarioService;

@RestController
@RequestMapping(value = "/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping
    public ResponseEntity listarUsuarios(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping
    public ResponseEntity criarUsuario(@RequestBody Usuario usuario){
        try{
            usuario.setPerfil("COLABORADOR");
            return ResponseEntity.ok(usuarioService.save(usuario));
        }catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}