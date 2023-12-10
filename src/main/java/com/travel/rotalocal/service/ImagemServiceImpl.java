package com.travel.rotalocal.service;

import org.springframework.stereotype.Service;

import com.travel.rotalocal.exception.ImagemNotFoundException;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.entity.Imagem;
import com.travel.rotalocal.model.entity.Usuario;
import com.travel.rotalocal.model.repository.AtracaoRepository;
import com.travel.rotalocal.model.repository.ImagemRepository;
import com.travel.rotalocal.model.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ImagemServiceImpl implements ImagemService {

    ImagemRepository imagemRepository;
    AtracaoRepository atracaoRepository;
    UsuarioRepository usuarioRepository;

    //metodo auxiliar
        static Imagem unwrapImagem(Optional<Imagem> entity, Long usuarioId, Long atracaoId) {
        if (entity.isPresent()) return entity.get();
        else throw new ImagemNotFoundException(usuarioId, atracaoId);
    }


    @Override
    public List<Imagem> getImagens(Long usuarioId, Long atracaoId) {
    List<Imagem> imagens = imagemRepository.findByUsuarioIdAndAtracaoId(usuarioId, atracaoId);
    return imagens;
    }

    @Override 
    public Imagem getImagem(Long imagemId){
        return imagemRepository.findById(imagemId).orElse(null);
    }
   

    @Override
    public List<Imagem> saveImagens(List<Imagem> imagens, Long usuarioId, Long atracaoId) {
        Usuario usuario = UsuarioServiceImpl.unwrapUsuario(usuarioRepository.findById(usuarioId), usuarioId);
        Atracao atracao = AtracaoServiceImpl.unwrapAtracao(atracaoRepository.findById(atracaoId), usuarioId, atracaoId);

        List<Imagem> savedImagens = new ArrayList<>();

        for (Imagem imagem : imagens) {
            imagem.setUsuario(usuario);
            imagem.setAtracao(atracao);
            savedImagens.add(imagemRepository.save(imagem));
        }

        return savedImagens;
    }
    
    //TODO - updateImagen

    @Override
    public void deleteImagem(Long usuarioId, Long atracaoId) {
        imagemRepository.deleteByUsuarioIdAndAtracaoId(usuarioId, atracaoId);
    }

    @Override
    public List<Imagem> getUsuarioImagens(Long usuarioId) {
        return imagemRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Imagem> getAtracaoImagens(Long atracaoId) {
        return imagemRepository.findByAtracaoId(atracaoId);
    }

    @Override
    public List<Imagem> getAllImagens() {
        return (List<Imagem>)imagemRepository.findAll();
    }
}

