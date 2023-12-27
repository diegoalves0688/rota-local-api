package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.model.entity.Usuario;

public interface UsuarioService {

    Usuario getUsuario(Long id);

    Usuario getUsuarioByEmail(String email);

    List<Usuario> getUsuarios();

    Usuario saveUsuario(Usuario usuario);

    void deleteUsuario(Long id);

    Usuario updateUsuario(Long id, Usuario updatedUsuario);

    // regra de negocio
    void registerUsuario(Usuario usuario);

}
