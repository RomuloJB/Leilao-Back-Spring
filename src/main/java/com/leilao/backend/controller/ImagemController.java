package com.leilao.backend.controller;

import com.leilao.backend.model.Imagem;
import com.leilao.backend.service.ImagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    private ImagemService imagemService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('VENDEDOR')")
    public ResponseEntity<Imagem> criar(@RequestBody @Valid Imagem imagem) {
        return ResponseEntity.ok(imagemService.criar(imagem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagem> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(imagemService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Imagem>> listarTodos() {
        return ResponseEntity.ok(imagemService.listarTodos());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('VENDEDOR')")
    public ResponseEntity<Imagem> atualizar(@PathVariable Long id, @RequestBody @Valid Imagem imagem) {
        return ResponseEntity.ok(imagemService.atualizar(id, imagem));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        imagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}