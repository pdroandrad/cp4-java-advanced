package com.example.MiniMercado.controller;

import com.example.MiniMercado.dto.ProdutoRequestDto;
import com.example.MiniMercado.dto.ProdutoResponseDto;
import com.example.MiniMercado.dto.ProdutoUpdateDto;

import com.example.MiniMercado.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        List<ProdutoResponseDto> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Busca um produto pelo Id")
    // GET /api/produtos/{id}
    @GetMapping("/id/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarPorId(@PathVariable Long id){
        return produtoService.listarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Busca produtos pelo nome")
    // GET /api/produtos/{nome}
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutoResponseDto>> buscarPorNome(@PathVariable String nome){
        List<ProdutoResponseDto> produtos = produtoService.listarPorNome(nome);
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Busca produtos pelo tipo")
    // GET /api/produtos/{tipo}
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ProdutoResponseDto>> buscarPorTipo(@PathVariable String tipo){
        List<ProdutoResponseDto> produtos = produtoService.listarPorTipo(tipo);
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Cadastra um novo produto")
    // POST /api/produtos
    @PostMapping
    public ResponseEntity<ProdutoResponseDto> criarProduto(@RequestBody @Valid ProdutoRequestDto dto){
        ProdutoResponseDto salvo = produtoService.criarProduto(dto);
        return ResponseEntity.ok(salvo);
    }

    @Operation(summary = "Atualiza um produto pelo Id")
    // PUT /api/produtos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDto dto){
        ProdutoResponseDto atualizado = produtoService.atualizarProduto(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Atualiza parcialmente um produto pelo Id")
    // PATCH /api/produtos/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> atualizarParcialProduto(@PathVariable Long id, @RequestBody ProdutoUpdateDto dto) {
        ProdutoResponseDto atualizadoparcial = produtoService.atualizarParcialProduto(id, dto);
        return ResponseEntity.ok(atualizadoparcial);
    }

    @Operation(summary = "Deleta um produto pelo Id")
    // DELETE /api/produtos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}
