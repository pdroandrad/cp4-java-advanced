package com.example.MiniMercado.model;

import com.example.MiniMercado.utils.Tamanho;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

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
    @Size(max = 30, message = "O nome não pode ter mais de 30 caracteres.")
    @Column(nullable = false, length = 30)
    private String nome;

    @NotBlank
    @Size(max = 30, message = "O tipo não pode ter mais de 30 caracteres.")
    @Column(nullable = false, length = 30)
    private String tipo;

    @NotBlank
    @Size(max = 30, message = "O setor não pode ter mais de 30 caracteres.")
    @Column(nullable = false, length = 30)
    private String setor;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Tamanho tamanho;

    @NotNull
    @Digits(integer = 5, fraction = 2)
    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal preco;
}
