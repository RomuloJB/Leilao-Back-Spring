package com.leilao.backend.service;

import com.leilao.backend.model.Imagem;
import com.leilao.backend.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
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
        Imagem imagem = buscarPorId(id);
        imagemRepository.delete(imagem);
    }
}