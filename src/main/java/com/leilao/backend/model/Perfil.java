package com.leilao.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    private String nome;
}
