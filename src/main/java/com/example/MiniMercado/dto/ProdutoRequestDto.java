package com.example.MiniMercado.dto;

import com.example.MiniMercado.utils.Tamanho;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProdutoRequestDto {

    @Schema(
            description="Nome do produto",
            example="Sal Cisne Refinado"
    )
    @NotBlank(message = "O nome do produto é obrigatório!")
    private String nome;

    @Schema(
            description="Categoria do produto",
            example="Temperos e Condimentos"
    )
    @NotBlank(message = "O tipo do produto é obrigatório!")
    private String tipo;

    @Schema(
            description="Setor do produto no mercado",
            example="Alimentos básicos"
    )
    @NotBlank(message = "O setor do produto é obrigatório!")
    private String setor;

    @Schema(
            description="Tamanho do produto (em peso ou volume)",
            example="1kg"
    )
    @NotNull(message = "O tamanho do produto é obrigatório!")
    private Tamanho tamanho;

    @Schema(
            description="Preço do produto",
            example="4.29"
    )
    @NotNull(message = "O preço do produto é obrigatório!")
    private Double preco;
}
