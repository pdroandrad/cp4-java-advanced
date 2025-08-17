package com.example.MiniMercado.controller;

import com.example.MiniMercado.dto.ProdutoRequestDto;
import com.example.MiniMercado.dto.ProdutoResponseDto;
import com.example.MiniMercado.model.Produto;
import com.example.MiniMercado.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
@Tag(name="Produto", description = "API para cadastro, alteração e listagem de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Lista todos os produtos")
    // GET /api/produtos
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarTodos(){
        List<ProdutoResponseDto> produtos = produtoService.listarTodosProdutos()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Busca um produto pelo Id")
    // GET /api/produtos/{id}
    @GetMapping("/id/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarPorId(@PathVariable Long id){
        return produtoService.listarPorId(id)
                .map(this::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Busca produtos pelo nome")
    // GET /api/produtos/{nome}
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutoResponseDto>> buscarPorNome(@PathVariable String nome){
        List<ProdutoResponseDto> produtos = produtoService.listarPorNome(nome)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Busca produtos pelo tipo")
    // GET /api/produtos/{tipo}
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ProdutoResponseDto>> buscarPorTipo(@PathVariable String tipo){
        List<ProdutoResponseDto> produtos = produtoService.listarPorTipo(tipo)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Cadastra um novo produto")
    // POST /api/produtos
    @PostMapping
    public ResponseEntity<ProdutoResponseDto> criarProduto(@RequestBody @Valid ProdutoRequestDto dto){
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setTipo(dto.getTipo());
        produto.setSetor(dto.getSetor());
        produto.setTamanho(dto.getTamanho());
        produto.setPreco(dto.getPreco());

        Produto salvo = produtoService.criarProduto(produto);
        return ResponseEntity.ok(toResponseDto(salvo));

    }

    @Operation(summary = "Atualiza um produto pelo Id")
    // PUT /api/produtos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDto dto){
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setTipo(dto.getTipo());
        produto.setSetor(dto.getSetor());
        produto.setTamanho(dto.getTamanho());
        produto.setPreco(dto.getPreco());

        Produto atualizado = produtoService.atualizarProduto(id, produto);
        return ResponseEntity.ok(toResponseDto(atualizado));
    }

    @Operation(summary = "Deleta um produto pelo Id")
    // DELETE /api/produtos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
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
