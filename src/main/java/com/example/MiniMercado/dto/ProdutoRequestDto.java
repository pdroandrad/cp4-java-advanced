package com.example.MiniMercado.dto;

import com.example.MiniMercado.utils.Tamanho;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProdutoRequestDto {

    @NotBlank(message = "O nome do produto é obrigatório!")
    private String nome;

    @NotBlank(message = "O tipo do produto é obrigatório!")
    private String tipo;

    @NotBlank(message = "O setor do produto é obrigatório!")
    private String setor;

    @NotNull(message = "O tamanho do produto é obrigatório!")
    private Tamanho tamanho;

    @NotNull(message = "O preço do produto é obrigatório!")
    private Double preco;
}
