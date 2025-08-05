package com.leilao.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.leilao.backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
}