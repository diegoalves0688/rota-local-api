package com.travel.rotalocal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.rotalocal.config.Tokens;
import com.travel.rotalocal.dto.AuthDTO;
import com.travel.rotalocal.dto.UsuarioDTO;
import com.travel.rotalocal.exception.EmailAlreadyRegisteredException;
import com.travel.rotalocal.exception.UsuarioNotFoundException;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.service.UsuarioService;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    /**********************************
     * GET
     **********************************/
    // VALIDADO POSTMAN
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuario(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    /**********************************
     * POST
     **********************************/
    // VALIDADO POSTMAN - ENDPOINT COM TRAVA DE EMAIL
    @PostMapping("/cadastro")
    public ResponseEntity<String> registerUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.registerUsuario(usuario);
            return new ResponseEntity<>("usuario registrado com sucesso", HttpStatus.OK);
        } catch (EmailAlreadyRegisteredException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // VALIDADO POSTMAN
    @PostMapping
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioService.saveUsuario(usuario);
            return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("erro ao salvar usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**********************************
     * DELETE
     **********************************/
    // VALIDADO POSTMAN
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**********************************
     * UPDATE
     **********************************/
    // VALIDADO POSTMAN
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody Usuario updatedUsuario) {
        try {
            Usuario updatedUsuarioResult = usuarioService.updateUsuario(id, updatedUsuario);
            return new ResponseEntity<>(updatedUsuarioResult, HttpStatus.OK);
        } catch (UsuarioNotFoundException e) {
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioService.getUsuarioByEmail(usuarioDTO.getEmail());
            if (usuario != null && usuario.getSenha().equals(usuarioDTO.getSenha())) {
                String token = Tokens.getInstance().tokensList.get(usuario.getId());
                if (token != null) {
                    return new ResponseEntity<>(new AuthDTO(usuario.getId(), token, usuario.getPerfil().toString()), HttpStatus.OK);
                } else {
                    String newToken = UUID.randomUUID().toString();
                    Tokens.getInstance().tokensList.put(usuario.getId(), newToken);
                    return new ResponseEntity<>(new AuthDTO(usuario.getId(), newToken, usuario.getPerfil().toString()), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        } catch (UsuarioNotFoundException e) {
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    /**********************************
     * AUXILIAR
     **********************************/

}