package com.travel.rotalocal.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.travel.rotalocal.model.entity.Imagem;
import jakarta.transaction.Transactional;

public interface ImagemRepository extends CrudRepository<Imagem, Long> {

    List<Imagem> findByUsuarioIdAndAtracaoId(Long usuarioId, Long atracaoId);

    @Transactional
    void deleteByUsuarioIdAndAtracaoId(Long usuarioId, Long atracaoId);
         
    List<Imagem> findByUsuarioId(Long usuarioId);
    List<Imagem> findByAtracaoId(Long atracaoId);
    
}
