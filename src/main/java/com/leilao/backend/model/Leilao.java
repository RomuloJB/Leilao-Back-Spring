package com.leilao.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "leilao")
public class Leilao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 2, max = 100, message = "Título deve ter entre 2 e 100 caracteres")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    private String descricao;

    @Size(max = 1000, message = "Descrição detalhada deve ter no máximo 1000 caracteres")
    private String descricaoDetalhada;

    @NotNull(message = "Data de início é obrigatória")
    private LocalDateTime dataHoraInicio;

    @NotNull(message = "Data de fim é obrigatória")
    private LocalDateTime dataHoraFim;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    private StatusLeilao status;

    @Size(max = 255, message = "Observação deve ter no máximo 255 caracteres")
    private String observacao;

    @NotNull(message = "Valor de incremento é obrigatório")
    @Min(value = 0, message = "Valor de incremento deve ser maior ou igual a zero")
    private Float valorIncremento;

    @NotNull(message = "Lance mínimo é obrigatório")
    @Min(value = 0, message = "Lance mínimo deve ser maior ou igual a zero")
    private Float lanceMinimo;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "publicador_id", nullable = false)
    private Pessoa publicador;

    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL)
    private List<Imagem> imagens;

    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL)
    private List<Lance> lances;

    @OneToOne(mappedBy = "leilao", cascade = CascadeType.ALL)
    private Pagamento pagamento;
}