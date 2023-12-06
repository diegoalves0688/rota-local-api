package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.model.entity.Usuario;

public interface UsuarioService {

    Usuario getUsuario(Long id);
    Usuario saveUsuario(Usuario usuario);
    void deleteUsuario(Long id);
    List<Usuario> getUsuarios();

    //regra de negocio
    void registerUsuario(Usuario usuario);

}
