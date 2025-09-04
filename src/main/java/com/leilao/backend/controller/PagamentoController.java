package com.leilao.backend.controller;

import com.leilao.backend.model.Pagamento;
import com.leilao.backend.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('COMPRADOR')")
    public ResponseEntity<Pagamento> criar(@RequestBody @Valid Pagamento pagamento) {
        return ResponseEntity.ok(pagamentoService.criar(pagamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Pagamento> atualizar(@PathVariable Long id, @RequestBody @Valid Pagamento pagamento) {
        return ResponseEntity.ok(pagamentoService.atualizar(id, pagamento));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}