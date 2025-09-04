package com.leilao.backend.controller;

import com.leilao.backend.model.Lance;
import com.leilao.backend.service.LanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lances")
public class LanceController {

    @Autowired
    private LanceService lanceService;

    @PostMapping
    @PreAuthorize("hasAuthority('COMPRADOR')")
    public ResponseEntity<Lance> criar(@RequestBody @Valid Lance lance) {
        return ResponseEntity.ok(lanceService.criar(lance));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lance> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(lanceService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Lance>> listarTodos() {
        return ResponseEntity.ok(lanceService.listarTodos());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Lance> atualizar(@PathVariable Long id, @RequestBody @Valid Lance lance) {
        return ResponseEntity.ok(lanceService.atualizar(id, lance));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        lanceService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}