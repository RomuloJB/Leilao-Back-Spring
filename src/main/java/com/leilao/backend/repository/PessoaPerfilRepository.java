package com.leilao.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leilao.backend.model.PessoaPerfil;


public interface PessoaPerfilRepository extends JpaRepository<PessoaPerfil, Long> {
    
}