package com.example.MiniMercado.dto;

import com.example.MiniMercado.utils.Tamanho;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProdutoResponseDto {

    private Long id;
    private String nome;
    private String tipo;
    private String setor;
    private Tamanho tamanho;
    private BigDecimal preco;

}
