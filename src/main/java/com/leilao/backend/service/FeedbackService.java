package com.leilao.backend.service;

import com.leilao.backend.model.Feedback;
import com.leilao.backend.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback criar(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback buscarPorId(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feedback não encontrado"));
    }

    public List<Feedback> listarTodos() {
        return feedbackRepository.findAll();
    }

    public Feedback atualizar(Long id, Feedback feedback) {
        Feedback existente = buscarPorId(id);
        existente.setComentario(feedback.getComentario());
        existente.setNota(feedback.getNota());
        existente.setDataHora(feedback.getDataHora());
        existente.setAutor(feedback.getAutor());
        existente.setDestinatario(feedback.getDestinatario());
        return feedbackRepository.save(existente);
    }

    public void deletar(Long id) {
        Feedback feedback = buscarPorId(id);
        feedbackRepository.delete(feedback);
    }
}