package com.travel.rotalocal.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.model.repository.UsuarioRepository;
import com.travel.rotalocal.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
    
}
