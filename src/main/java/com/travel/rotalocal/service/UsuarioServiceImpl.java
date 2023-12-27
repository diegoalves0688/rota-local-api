package com.travel.rotalocal.service;

import org.springframework.stereotype.Service;

import com.travel.rotalocal.exception.EmailAlreadyRegisteredException;
import com.travel.rotalocal.exception.UsuarioNotFoundException;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.model.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    UsuarioRepository usuarioRepository;

    /**********************************
     * GET
     **********************************/
    @Override
    public Usuario getUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return unwrapUsuario(usuario, id);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public void registerUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new EmailAlreadyRegisteredException(usuario.getEmail());
        }
        usuarioRepository.save(usuario);
    }

    /**********************************
     * SAVE
     **********************************/
    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**********************************
     * DELETE
     **********************************/
    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**********************************
     * UPDATE
     **********************************/
    @Override
    public Usuario updateUsuario(Long id, Usuario updatedUsuario) {
        Optional<Usuario> existingUsuarioOptional = usuarioRepository.findById(id);
        Usuario existingUsuario = unwrapUsuario(existingUsuarioOptional, id);

        // CAMPOS PERMITIDOS PARA SEREM ATUALIZADOS
        existingUsuario.setNome(updatedUsuario.getNome());
        existingUsuario.setSenha(updatedUsuario.getSenha());
        existingUsuario.setFoto(updatedUsuario.getFoto());
        existingUsuario.setAtivo(updatedUsuario.isAtivo());

        return usuarioRepository.save(existingUsuario);
    }

    /**********************************
     * AUXILIAR
     **********************************/
    // METODO AUXILIAR
    static Usuario unwrapUsuario(Optional<Usuario> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new UsuarioNotFoundException(id);
    }

    @Override
    public Usuario getUsuarioByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        return usuario;
    }
}