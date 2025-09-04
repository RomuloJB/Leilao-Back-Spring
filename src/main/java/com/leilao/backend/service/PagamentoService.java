package com.leilao.backend.service;

import com.leilao.backend.model.Pagamento;
import com.leilao.backend.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento criar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento buscarPorId(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado"));
    }

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento atualizar(Long id, Pagamento pagamento) {
        Pagamento existente = buscarPorId(id);
        existente.setValor(pagamento.getValor());
        existente.setDataHora(pagamento.getDataHora());
        existente.setStatus(pagamento.getStatus());
        existente.setLeilao(pagamento.getLeilao());
        return pagamentoRepository.save(existente);
    }

    public void deletar(Long id) {
        Pagamento pagamento = buscarPorId(id);
        pagamentoRepository.delete(pagamento);
    }
}