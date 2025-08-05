package com.leilao.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leilao.backend.exception.NaoEncontradoExcecao;
import com.leilao.backend.model.Categoria;
import com.leilao.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MessageSource messageSource;

    public Categoria inserir(Categoria categoria) {
        Categoria categoriaCadastrada = categoriaRepository.save(categoria);
        return categoriaCadastrada;
    }

    public Categoria alterar(Categoria categoria) {
        Categoria categoriaBanco = buscarPorId(categoria.getId());
        categoriaBanco.setNome(categoria.getNome());
        return categoriaRepository.save(categoriaBanco);
    }

    public void excluir(Long id) {
        Categoria categoriaBanco = buscarPorId(id);
        categoriaRepository.delete(categoriaBanco);
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new NaoEncontradoExcecao(messageSource.getMessage("categoria.notfound", 
        new Object[] { id }, LocaleContextHolder.getLocale())));
    }

    public Page<Categoria> buscarTodos(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }
}
