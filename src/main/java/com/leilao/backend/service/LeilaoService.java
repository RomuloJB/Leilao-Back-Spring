package com.leilao.backend.service;

import com.leilao.backend.model.Leilao;
import com.leilao.backend.repository.LeilaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class LeilaoService {

    @Autowired
    private LeilaoRepository leilaoRepository;

    public Leilao criar(Leilao leilao) {
        return leilaoRepository.save(leilao);
    }

    public Leilao buscarPorId(Long id) {
        return leilaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leilao não encontrado"));
    }

    public List<Leilao> listarTodos() {
        return leilaoRepository.findAll();
    }

    public Leilao atualizar(Long id, Leilao leilao) {
        Leilao existente = buscarPorId(id);
        existente.setTitulo(leilao.getTitulo());
        existente.setDescricao(leilao.getDescricao());
        existente.setDescricaoDetalhada(leilao.getDescricaoDetalhada());
        existente.setDataHoraInicio(leilao.getDataHoraInicio());
        existente.setDataHoraFim(leilao.getDataHoraFim());
        existente.setStatus(leilao.getStatus());
        existente.setObservacao(leilao.getObservacao());
        existente.setValorIncremento(leilao.getValorIncremento());
        existente.setLanceMinimo(leilao.getLanceMinimo());
        existente.setCategoria(leilao.getCategoria());
        existente.setPublicador(leilao.getPublicador());
        // Note: listas como imagens, lances, pagamento precisam de manejo separado se
        // necessário
        return leilaoRepository.save(existente);
    }

    public void deletar(Long id) {
        Leilao leilao = buscarPorId(id);
        leilaoRepository.delete(leilao);
    }
}