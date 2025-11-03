package com.leilao.backend.service;

import com.leilao.backend.model.Lance;
import com.leilao.backend.repository.LanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class LanceService {

    @Autowired
    private LanceRepository lanceRepository;


    public Lance criar(Lance lance) {
        log.info("Criando um novo lance: {}", lance);
        return lanceRepository.save(lance);
    }

    public Lance buscarPorId(Long id) {
        log.info("Buscando lance pelo ID: {}", id);
        return lanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lance não encontrado"));
    }

    public List<Lance> listarTodos() {
        log.info("Listando todos os lances");
        return lanceRepository.findAll();
    }

    public Lance atualizar(Long id, Lance lance) {
        log.info("Atualizando lance com ID: {}", id);
        Lance existente = buscarPorId(id);
        existente.setValorLance(lance.getValorLance());
        existente.setDataHora(lance.getDataHora());
        existente.setLeilao(lance.getLeilao());
        existente.setLanceador(lance.getLanceador());
        return lanceRepository.save(existente);
    }

    public void deletar(Long id) {
        log.info("Deletando lance com ID: {}", id);
        log.warn("Atenção: Ao excluir um lance, você não poderá acessá-lo novamente.");
        Lance lance = buscarPorId(id);
        lanceRepository.delete(lance);
    }
}