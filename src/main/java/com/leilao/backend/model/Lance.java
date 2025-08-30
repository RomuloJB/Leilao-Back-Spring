package com.leilao.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "lance")
public class Lance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Valor do lance é obrigatório")
    @Min(value = 0, message = "Valor do lance deve ser maior ou igual a zero")
    private Float valorLance;

    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "leilao_id", nullable = false)
    private Leilao leilao;

    @ManyToOne
    @JoinColumn(name = "lanceador_id", nullable = false)
    private Pessoa lanceador;
}