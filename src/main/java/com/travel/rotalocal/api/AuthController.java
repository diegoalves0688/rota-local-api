package com.travel.rotalocal.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.rotalocal.dto.UsuarioDTO;
import com.travel.rotalocal.exception.UsuarioNotFoundException;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.service.UsuarioService;
import com.travel.rotalocal.config.Tokens;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/autenticar")
public class AuthController {
    
    private UsuarioService usuarioService;
    
    @PostMapping
    public ResponseEntity<?> autenticarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioService.getUsuarioByEmail(usuarioDTO.getEmail());
            if (usuario != null && usuario.getSenha().equals(usuarioDTO.getSenha())) {
                String token = Tokens.getInstance().tokensList.get(usuario.getId());
                if (token != null) {
                    return new ResponseEntity<>(token, HttpStatus.OK);
                } else {
                    String newToken = UUID.randomUUID().toString();
                    Tokens.getInstance().tokensList.put(usuario.getId(), newToken);
                    return new ResponseEntity<>(newToken, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        } catch (UsuarioNotFoundException e) {
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }

}
