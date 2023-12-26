package com.travel.rotalocal.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.travel.rotalocal.dto.ImagemDTO;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.model.entity.Imagem;
import com.travel.rotalocal.service.AtracaoService;
import com.travel.rotalocal.service.ImagemService;

import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/imagem")
public class ImagemController {

    @Autowired
    private HttpServletRequest request;

    private ImagemService imagemService;

    AtracaoService atracaoService;

    /**********************************
     * GET
     **********************************/
    // VALIDADO POSTMAN
    @GetMapping
    public ResponseEntity<List<Imagem>> getAllImagens() {
        List<Imagem> imagens = imagemService.getAllImagens();
        return new ResponseEntity<>(imagens, HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("/{id}")
    public ResponseEntity<Imagem> getImagem(@PathVariable Long id) {
        return new ResponseEntity<>(imagemService.getImagem(id), HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<List<Imagem>> getImagens(@PathVariable Long usuarioId, @PathVariable Long atracaoId) {
        List<Imagem> imagens = imagemService.getImagens(usuarioId, atracaoId);
        return new ResponseEntity<>(imagens, HttpStatus.OK);
    }

    // VALIDADO POSTMAN
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Imagem>> getUsuarioImagens(@PathVariable Long usuarioId) {
        List<Imagem> imagens = imagemService.getUsuarioImagens(usuarioId);
        return new ResponseEntity<>(imagens, HttpStatus.OK);
    }

    /**********************************
     * POST
     **********************************/
    // VALIDADO POSTMAN
    @PostMapping("usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<List<Imagem>> createImagem(
            @RequestBody List<Imagem> imagens,
            @PathVariable Long usuarioId,
            @PathVariable Long atracaoId) {
        List<Imagem> savedImagens = imagemService.saveImagens(imagens, usuarioId, atracaoId);
        return new ResponseEntity<>(savedImagens, HttpStatus.CREATED);
    }

    //VALIDADO POSTMAN - DÚVIDA: COMO QUE ISSO SERA REFLETIDO NO DB?
    @PostMapping
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file,
        @RequestParam("usuario") Long usuarioId, @RequestParam("atracao") Long atracaoId) {

        String fileName = "";

        if (!file.isEmpty()) {
            try {
                String uploadsDir = "/images/";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }
                fileName = file.getOriginalFilename();
                String filePath = realPathtoUploads + fileName;
                File dest = new File(filePath);
                file.transferTo(dest);
            } catch (Exception e) {
            }
        }

        List<Imagem> imageList = new ArrayList();
        Imagem imagem = new Imagem();
        imagem.setNome(file.getOriginalFilename());
        imagem.setUrlCaminho(fileName);
        imageList.add(imagem);
        List<Imagem> imagens = imagemService.saveImagens(imageList, usuarioId, atracaoId);

        return ResponseEntity.ok(imagens);
    }

    /**********************************
     * DELETE
     **********************************/
    // VALIDADO POSTMAN - vai deletar tudo da combinacao [atracao + usuario]
    // TODO - VERIFICAR COMO FICARA ISSO SE A PESSOA POSTA MAIS DE UMA FOTO
    @DeleteMapping("/usuario/{usuarioId}/atracao/{atracaoId}")
    public ResponseEntity<HttpStatus> deleteImagem(@PathVariable Long usuarioId, @PathVariable Long atracaoId) {
        imagemService.deleteImagem(usuarioId, atracaoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**********************************
     * UPDATE
     **********************************/
    // TODO

}
