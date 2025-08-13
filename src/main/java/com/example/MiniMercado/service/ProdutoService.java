package com.example.MiniMercado.service;

import com.example.MiniMercado.exception.ResourceNotFoundException;
import com.example.MiniMercado.model.Produto;
import com.example.MiniMercado.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // CREATE
    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // READ - TODOS
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    // READ - POR ID
    public Optional<Produto> listarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    // READ - POR NOME
    public List<Produto> listarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    // READ - POR TIPO
    public List<Produto> listarPorTipo(String tipo) {
        return produtoRepository.findByTipoContainingIgnoreCase(tipo);
    }

    // UPDATE
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setTipo(produtoAtualizado.getTipo());
            produto.setSetor(produtoAtualizado.getSetor());
            produto.setTamanho(produtoAtualizado.getTamanho());
            produto.setPreco(produtoAtualizado.getPreco());
            return produtoRepository.save(produto);
        }).orElseThrow(()-> new ResourceNotFoundException("Produto", "id", id));
    }

    // DELETE
    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto", "id", id);
        }
        produtoRepository.deleteById(id);
    }
}
