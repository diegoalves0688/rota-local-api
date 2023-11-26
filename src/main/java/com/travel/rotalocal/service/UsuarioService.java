package com.travel.rotalocal.service;

import java.util.List;

import com.travel.rotalocal.model.entity.Usuario;

public interface UsuarioService {
    public List<Usuario> findAll();

    public Usuario save(Usuario atracao);
}
