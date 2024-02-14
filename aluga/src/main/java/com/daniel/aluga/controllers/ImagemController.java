package com.daniel.aluga.controllers;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daniel.aluga.models.ImagemEntity;
import com.daniel.aluga.repositories.ImagemRepository;

@RestController
public class ImagemController {
    @Autowired
    private ImagemRepository imagemRepository;

   
private String bytesToBase64(byte[] bytes) {
    return Base64.getEncoder().encodeToString(bytes);
}

/
private byte[] base64ToBytes(String base64) {
    return Base64.getDecoder().decode(base64);
}


@PostMapping("/salvar-imagem/{tipo}")
public ResponseEntity<String> salvarImagem(@RequestPart("imagem") MultipartFile imagem, @PathVariable("tipo") String tipo) {
    if (imagem.isEmpty()) {
        return new ResponseEntity<>("Arquivo vazio", HttpStatus.BAD_REQUEST);
    }

    try {
        byte[] imagemBytes = imagem.getBytes();
        String imagemBase64 = bytesToBase64(imagemBytes);

        ImagemEntity imagemEntity = new ImagemEntity();
        imagemEntity.setImagemBase64(imagemBase64); // Salvar a imagem em Base64
        imagemEntity.setTipo(tipo);
        imagemRepository.save(imagemEntity);
        return new ResponseEntity<>("Imagem salva com sucesso!", HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Erro ao processar a imagem", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


@GetMapping("/imagem/{tipo}")
public ResponseEntity<?> getImagemPorTipo(@PathVariable("tipo") String tipo) {
    Optional<ImagemEntity> imagemOptional = imagemRepository.findByTipo(tipo);
    if (imagemOptional.isPresent()) {
        ImagemEntity imagem = imagemOptional.get();
        String imagemBase64 = imagem.getImagemBase64(); 
        byte[] imageData = base64ToBytes(imagemBase64);
        return ResponseEntity.ok(imageData);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem não encontrada para o tipo: " + tipo);
    }
}

}