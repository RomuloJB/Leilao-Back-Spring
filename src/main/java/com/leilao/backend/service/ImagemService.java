package com.leilao.backend.service;

import com.leilao.backend.model.Imagem;
import com.leilao.backend.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class ImagemService {

    @Autowired
    private ImagemRepository imagemRepository;

    public Imagem criar(Imagem imagem) {
        return imagemRepository.save(imagem);
    }

    public Imagem buscarPorId(Long id) {
        return imagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imagem não encontrada encontrada"));
    }

    public List<Imagem> listarTodos() {
        return imagemRepository.findAll();
    }

    public Imagem atualizar(Long id, Imagem imagem) {
        Imagem existente = buscarPorId(id);
        existente.setDataHoraCadastro(imagem.getDataHoraCadastro());
        existente.setNomeImagem(imagem.getNomeImagem());
        existente.setLeilao(imagem.getLeilao());
        return imagemRepository.save(existente);
    }

    public void deletar(Long id) {
        log.info("Deletando imagem com ID: {}", id);
        log.warn("Atenção: Ao excluir uma imagem, você não poderá acessá-la novamente.");
        Imagem imagem = buscarPorId(id);
        imagemRepository.delete(imagem);
    }
}