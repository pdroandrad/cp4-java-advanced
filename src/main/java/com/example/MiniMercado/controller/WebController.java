package com.example.MiniMercado.controller;

import com.example.MiniMercado.dto.ProdutoRequestDto;
import com.example.MiniMercado.dto.ProdutoResponseDto;
import com.example.MiniMercado.service.ProdutoService;
import com.example.MiniMercado.utils.Tamanho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class WebController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String home(Model model) {
        List<ProdutoResponseDto> produtos = produtoService.listarTodosProdutos();
        model.addAttribute("produtos", produtos);
        return "index";
    }

    @GetMapping("/produtos")
    public String listarProdutos(Model model, @RequestParam(required = false) String busca) {
        List<ProdutoResponseDto> produtos;
        
        if (busca != null && !busca.trim().isEmpty()) {
            produtos = produtoService.listarPorNome(busca);
            model.addAttribute("busca", busca);
        } else {
            produtos = produtoService.listarTodosProdutos();
        }
        
        model.addAttribute("produtos", produtos);
        return "produtos/lista";
    }

    @GetMapping("/produtos/novo")
    public String novoProdutoForm(Model model) {
        model.addAttribute("produto", new ProdutoRequestDto());
        model.addAttribute("tamanhos", Tamanho.values());
        return "produtos/form";
    }

    @PostMapping("/produtos")
    public String criarProduto(@Valid @ModelAttribute("produto") ProdutoRequestDto produto, 
                              BindingResult result, 
                              Model model, 
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("tamanhos", Tamanho.values());
            return "produtos/form";
        }

        try {
            produtoService.criarProduto(produto);
            redirectAttributes.addFlashAttribute("sucesso", "Produto criado com sucesso!");
            return "redirect:/produtos";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao criar produto: " + e.getMessage());
            model.addAttribute("tamanhos", Tamanho.values());
            return "produtos/form";
        }
    }

    @GetMapping("/produtos/{id}")
    public String visualizarProduto(@PathVariable Long id, Model model) {
        Optional<ProdutoResponseDto> produto = produtoService.listarPorId(id);
        if (produto.isPresent()) {
            model.addAttribute("produto", produto.get());
            return "produtos/detalhes";
        }
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/{id}/editar")
    public String editarProdutoForm(@PathVariable Long id, Model model) {
        Optional<ProdutoResponseDto> produtoOpt = produtoService.listarPorId(id);
        if (produtoOpt.isPresent()) {
            ProdutoResponseDto produto = produtoOpt.get();
            ProdutoRequestDto produtoRequest = new ProdutoRequestDto();
            produtoRequest.setNome(produto.getNome());
            produtoRequest.setTipo(produto.getTipo());
            produtoRequest.setSetor(produto.getSetor());
            produtoRequest.setTamanho(produto.getTamanho());
            produtoRequest.setPreco(produto.getPreco());
            
            model.addAttribute("produto", produtoRequest);
            model.addAttribute("produtoId", id);
            model.addAttribute("tamanhos", Tamanho.values());
            model.addAttribute("editando", true);
            return "produtos/form";
        }
        return "redirect:/produtos";
    }

    @PostMapping("/produtos/{id}")
    public String atualizarProduto(@PathVariable Long id, 
                                  @Valid @ModelAttribute("produto") ProdutoRequestDto produto,
                                  BindingResult result, 
                                  Model model, 
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("produtoId", id);
            model.addAttribute("tamanhos", Tamanho.values());
            model.addAttribute("editando", true);
            return "produtos/form";
        }

        try {
            produtoService.atualizarProduto(id, produto);
            redirectAttributes.addFlashAttribute("sucesso", "Produto atualizado com sucesso!");
            return "redirect:/produtos";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao atualizar produto: " + e.getMessage());
            model.addAttribute("produtoId", id);
            model.addAttribute("tamanhos", Tamanho.values());
            model.addAttribute("editando", true);
            return "produtos/form";
        }
    }

    @PostMapping("/produtos/{id}/deletar")
    public String deletarProduto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            produtoService.deletarProduto(id);
            redirectAttributes.addFlashAttribute("sucesso", "Produto deletado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao deletar produto: " + e.getMessage());
        }
        return "redirect:/produtos";
    }
}