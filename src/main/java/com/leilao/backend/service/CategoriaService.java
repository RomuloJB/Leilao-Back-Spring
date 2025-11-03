package com.leilao.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leilao.backend.model.Categoria;
import com.leilao.backend.repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria criar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
    }

    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria atualizar(Long id, Categoria categoria) {
        Categoria existente = buscarPorId(id);
        existente.setNome(categoria.getNome());
        existente.setObservacao(categoria.getObservacao());
        existente.setCriador(categoria.getCriador());
        return categoriaRepository.save(existente);
    }

    public void deletar(Long id) {
        log.info("Deletando categoria com ID: {}", id);
        log.warn("Atenção: Ao excluir uma categoria, você não poderá acessá-la novamente.");
        Categoria categoria = buscarPorId(id);
        categoriaRepository.delete(categoria);
    }
}