package com.leilao.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Valor é obrigatório")
    @Min(value = 0, message = "Valor deve ser maior ou igual a zero")
    private Float valor;

    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    @OneToOne
    @JoinColumn(name = "leilao_id", nullable = false)
    private Leilao leilao;
}