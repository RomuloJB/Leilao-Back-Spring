package com.leilao.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leilao.backend.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    public Object findByNome(String nomePerfil);
}
