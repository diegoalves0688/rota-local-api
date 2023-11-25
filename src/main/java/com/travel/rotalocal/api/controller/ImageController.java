package com.travel.rotalocal.api.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travel.rotalocal.api.dto.ImageDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/images")
@CrossOrigin(origins = "*")
public class ImageController {
    
    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file) {

        String fileName = "";

        if (!file.isEmpty()) {
            try {
                String uploadsDir = "/images/";
                String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
                if(! new File(realPathtoUploads).exists())
                {
                    new File(realPathtoUploads).mkdir();
                }
                fileName = file.getOriginalFilename();
                String filePath = realPathtoUploads + fileName;
                File dest = new File(filePath);
                file.transferTo(dest);
            }catch(Exception e){}
        }

        return ResponseEntity.ok(new ImageDTO(fileName));
    }

}
