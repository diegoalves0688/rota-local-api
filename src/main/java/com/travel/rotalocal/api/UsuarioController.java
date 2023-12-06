package com.travel.rotalocal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.rotalocal.exception.EmailAlreadyRegisteredException;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.service.UsuarioService;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    //VALIDADO POSTMAN
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    //VALIDADO POSTMAN
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuario(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    //VALIDADO POSTMAN
    // BUG era por causa do postgres enum...
    @PostMapping
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioService.saveUsuario(usuario);
            return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("erro ao salvar usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     //VALIDADO POSTMAN - ENDPOINT COM TRAVA DE EMAIL
    @PostMapping("/cadastro")
    public ResponseEntity<String> registerUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.registerUsuario(usuario);
            return new ResponseEntity<>("usuario registrado com sucesso", HttpStatus.OK);
        } catch (EmailAlreadyRegisteredException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //VALIDADO POSTMAN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}