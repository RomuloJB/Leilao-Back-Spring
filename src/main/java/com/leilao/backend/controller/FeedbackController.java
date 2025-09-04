package com.leilao.backend.controller;

import com.leilao.backend.model.Feedback;
import com.leilao.backend.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    @PreAuthorize("hasAuthority('COMPRADOR') or hasAuthority('VENDEDOR')")
    public ResponseEntity<Feedback> criar(@RequestBody @Valid Feedback feedback) {
        return ResponseEntity.ok(feedbackService.criar(feedback));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> listarTodos() {
        return ResponseEntity.ok(feedbackService.listarTodos());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('COMPRADOR') or hasAuthority('VENDEDOR')")
    public ResponseEntity<Feedback> atualizar(@PathVariable Long id, @RequestBody @Valid Feedback feedback) {
        return ResponseEntity.ok(feedbackService.atualizar(id, feedback));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        feedbackService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}