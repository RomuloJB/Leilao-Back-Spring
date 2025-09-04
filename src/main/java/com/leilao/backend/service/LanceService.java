package com.leilao.backend.service;

import com.leilao.backend.model.Lance;
import com.leilao.backend.repository.LanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class LanceService {

    @Autowired
    private LanceRepository lanceRepository;

    public Lance criar(Lance lance) {
        return lanceRepository.save(lance);
    }

    public Lance buscarPorId(Long id) {
        return lanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lance não encontrado"));
    }

    public List<Lance> listarTodos() {
        return lanceRepository.findAll();
    }

    public Lance atualizar(Long id, Lance lance) {
        Lance existente = buscarPorId(id);
        existente.setValorLance(lance.getValorLance());
        existente.setDataHora(lance.getDataHora());
        existente.setLeilao(lance.getLeilao());
        existente.setLanceador(lance.getLanceador());
        return lanceRepository.save(existente);
    }

    public void deletar(Long id) {
        Lance lance = buscarPorId(id);
        lanceRepository.delete(lance);
    }
}