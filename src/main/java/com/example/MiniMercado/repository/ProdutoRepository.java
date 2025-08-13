package com.example.MiniMercado.repository;

import com.example.MiniMercado.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByTipoContainingIgnoreCase(String categoria);
}
