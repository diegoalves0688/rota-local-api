package com.travel.rotalocal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.rotalocal.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
