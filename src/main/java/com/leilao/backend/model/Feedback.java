package com.leilao.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Comentário é obrigatório")
    @Size(max = 500, message = "Comentário deve ter no máximo 500 caracteres")
    private String comentario;

    @NotNull(message = "Nota é obrigatória")
    @Min(value = 0, message = "Nota deve ser pelo menos 0")
    @Max(value = 10, message = "Nota deve ser no máximo 10")
    private Integer nota;

    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Pessoa autor;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Pessoa destinatario;
}