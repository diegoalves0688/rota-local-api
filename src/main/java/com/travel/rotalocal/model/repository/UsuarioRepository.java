package com.travel.rotalocal.model.repository;

import org.springframework.data.repository.CrudRepository;
import com.travel.rotalocal.model.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    
    //regra de negocio
    boolean existsByEmail(String email);
}