package com.travel.rotalocal.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.travel.rotalocal.model.entity.Imagem;
import com.travel.rotalocal.service.ImagemService;

import java.util.Optional;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/imagem")
public class ImagemController {

    private ImagemService imagemService;

    //VALIDADO POSTMAN
    @GetMapping
    public ResponseEntity<List<Imagem>> getAllImagens() {
        List<Imagem> imagens = imagemService.getAllImagens();
        return new ResponseEntity<>(imagens, HttpStatus.OK);
    }

    //VALIDADO POSTMAN
    @GetMapping("usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<List<Imagem>> getImagens(@PathVariable Long usuarioId, @PathVariable Long atracaoId) {
    List<Imagem> imagens = imagemService.getImagens(usuarioId, atracaoId);
    return new ResponseEntity<>(imagens, HttpStatus.OK);
    }


    //VALIDADO POSTMAN
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Imagem>> getUsuarioImagens(@PathVariable Long usuarioId) {
        List<Imagem> imagens = imagemService.getUsuarioImagens(usuarioId);
        return new ResponseEntity<>(imagens, HttpStatus.OK);
    }

    //VALIDADO POSTMAN para 1 - bug com n 
    @PostMapping("usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<List<Imagem>> createImagem(
            @RequestBody List<Imagem> imagens,//Imagem imagem,
            @PathVariable Long usuarioId,
            @PathVariable Long atracaoId
    ) {
        List<Imagem> savedImagens = imagemService.saveImagens(imagens, usuarioId, atracaoId);//Imagem savedImagem = imagemService.saveImagem(imagem, usuarioId, atracaoId);
        return new ResponseEntity<>(savedImagens, HttpStatus.CREATED); // return new ResponseEntity<>(savedImagem, HttpStatus.CREATED);
    }

    //VALIDADO POSTMAN - vai deletar tudo da combinacao [atracao + usuario]
    //TODO - VERIFICAR COMO FICARA ISSO SE A PESSOA POSTA MAIS DE UMA FOTO
    @DeleteMapping("/usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<HttpStatus> deleteImagem(@PathVariable Long usuarioId, @PathVariable Long atracaoId) {
        imagemService.deleteImagem(usuarioId, atracaoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
