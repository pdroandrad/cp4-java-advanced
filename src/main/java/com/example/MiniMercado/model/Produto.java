package com.example.MiniMercado.model;

import com.example.MiniMercado.utils.Tamanho;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String tipo;

    @NotBlank
    @Column(nullable = false)
    private String setor;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Tamanho tamanho;

    @NotNull
    @Column(nullable = false)
    private Double preco;
}
