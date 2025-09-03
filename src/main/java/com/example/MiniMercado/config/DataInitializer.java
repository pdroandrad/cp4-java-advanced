package com.example.MiniMercado.config;

import com.example.MiniMercado.model.Produto;
import com.example.MiniMercado.repository.ProdutoRepository;
import com.example.MiniMercado.utils.Tamanho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (produtoRepository.count() == 0) {
            // Adicionar alguns produtos de exemplo
            produtoRepository.save(new Produto(null, "Sal Cisne Refinado", "Temperos e Condimentos", "Alimentos básicos", Tamanho.M, new BigDecimal("4.29")));
            produtoRepository.save(new Produto(null, "Açúcar Cristal União", "Açúcar", "Alimentos básicos", Tamanho.G, new BigDecimal("3.89")));
            produtoRepository.save(new Produto(null, "Arroz Tio João", "Grãos", "Alimentos básicos", Tamanho.G, new BigDecimal("5.99")));
            produtoRepository.save(new Produto(null, "Feijão Carioca", "Grãos", "Alimentos básicos", Tamanho.M, new BigDecimal("7.50")));
            produtoRepository.save(new Produto(null, "Óleo de Soja Liza", "Óleos", "Alimentos básicos", Tamanho.M, new BigDecimal("6.79")));
            produtoRepository.save(new Produto(null, "Macarrão Espaguete", "Massas", "Alimentos básicos", Tamanho.M, new BigDecimal("2.99")));
        }
    }
}