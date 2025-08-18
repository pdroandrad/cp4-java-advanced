package com.example.MiniMercado.service;

import com.example.MiniMercado.dto.ProdutoRequestDto;
import com.example.MiniMercado.dto.ProdutoResponseDto;
import com.example.MiniMercado.dto.ProdutoUpdateDto;
import com.example.MiniMercado.exception.ResourceNotFoundException;
import com.example.MiniMercado.model.Produto;
import com.example.MiniMercado.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // CREATE
    public ProdutoResponseDto criarProduto(ProdutoRequestDto dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setTipo(dto.getTipo());
        produto.setSetor(dto.getSetor());
        produto.setTamanho(dto.getTamanho());
        produto.setPreco(dto.getPreco());

        Produto salvo = produtoRepository.save(produto);
        return toResponseDto(salvo);
    }

    // READ - TODOS
    public List<ProdutoResponseDto> listarTodosProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    // READ - POR ID
    public Optional<ProdutoResponseDto> listarPorId(Long id) {
        return produtoRepository.findById(id).map(this::toResponseDto);
    }

    // READ - POR NOME
    public List<ProdutoResponseDto> listarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    // READ - POR TIPO
    public List<ProdutoResponseDto> listarPorTipo(String tipo) {
        return produtoRepository.findByTipoContainingIgnoreCase(tipo)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    // UPDATE
    public ProdutoResponseDto atualizarProduto(Long id, @Valid ProdutoRequestDto dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "id", id));

        produto.setNome(dto.getNome());
        produto.setTipo(dto.getTipo());
        produto.setSetor(dto.getSetor());
        produto.setTamanho(dto.getTamanho());
        produto.setPreco(dto.getPreco());

        Produto atualizado = produtoRepository.save(produto);
        return toResponseDto(atualizado);
    }

    //PATCH
    public ProdutoResponseDto atualizarParcialProduto(Long id, ProdutoUpdateDto dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "id", id));

        if (dto.getNome() != null) {
            produto.setNome(dto.getNome());
        }
        if (dto.getTipo() != null) {
            produto.setTipo(dto.getTipo());
        }
        if (dto.getSetor() != null) {
            produto.setSetor(dto.getSetor());
        }
        if (dto.getTamanho() != null) {
            produto.setTamanho(dto.getTamanho());
        }
        if (dto.getPreco() != null) {
            produto.setPreco(dto.getPreco());
        }

        Produto atualizado = produtoRepository.save(produto);
        return toResponseDto(atualizado);
    }

    // DELETE
    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto", "id", id);
        }
        produtoRepository.deleteById(id);
    }


    // Metodo para converter dto
    private ProdutoResponseDto toResponseDto(Produto produto) {
        ProdutoResponseDto dto = new ProdutoResponseDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setTipo(produto.getTipo());
        dto.setSetor(produto.getSetor());
        dto.setTamanho(produto.getTamanho());
        dto.setPreco(produto.getPreco());
        return dto;
    }
}
