package com.example.MiniMercado.controller;

import com.example.MiniMercado.dto.ProdutoRequestDto;
import com.example.MiniMercado.dto.ProdutoResponseDto;
import com.example.MiniMercado.model.Produto;
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

    @GetMapping(path={"/", "/tipo"})
    public String index(String tipo, Model model) {
        if (tipo != null) {
            List<ProdutoResponseDto> produtos = produtoService.listarPorTipo(tipo);
            model.addAttribute("produtos", produtos);
        }
        else {
            List<ProdutoResponseDto> produtos = produtoService.listarTodosProdutos();
            model.addAttribute("produtos", produtos);
        }
        return "index";
    }

    // CADASTRO.HTML
    @GetMapping("/produtos/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new ProdutoRequestDto());
        return "produtos/cadastro";
    }
    // GET para mostrar o formulário de edição
    @GetMapping("/produtos/{id}/edit")
    public String editProduto(@PathVariable Long id, Model model) {
        Optional<ProdutoResponseDto> produtoOpt = produtoService.listarPorId(id);
        if (produtoOpt.isPresent()) {
            ProdutoResponseDto p = produtoOpt.get();

            ProdutoRequestDto formDto = new ProdutoRequestDto();
            formDto.setNome(p.getNome());
            formDto.setTipo(p.getTipo());
            formDto.setSetor(p.getSetor());
            formDto.setTamanho(p.getTamanho());
            formDto.setPreco(p.getPreco());

            model.addAttribute("produto", formDto);
            model.addAttribute("produtoId", p.getId());
            return "produtos/edit";
        }
        return "";
    }


    // POST PARA CRIAR PRODUTO
    @PostMapping("/produtos")
    public String criarProduto(@Valid @ModelAttribute("produto") ProdutoRequestDto produto,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "produtos/cadastro";
        }

        produtoService.criarProduto(produto);
        redirectAttributes.addFlashAttribute("sucesso", "Produto criado com sucesso!");
        return "redirect:/";
    }

    // POST para atualizar produto
    @PostMapping("/produtos/{id}/edit")
    public String atualizarProduto(@PathVariable Long id,
                                   @Valid @ModelAttribute("produto") ProdutoRequestDto produto,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "produtos/edit";
        }
        produtoService.atualizarProduto(id, produto);
        redirectAttributes.addFlashAttribute("sucesso", "Produto atualizado com sucesso!");
        return "redirect:/";
    }

    // POST PARA DELETAR PRODUTO
    @PostMapping("/produtos/{id}/delete")
    public String deletarProduto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            produtoService.deletarProduto(id);
            redirectAttributes.addFlashAttribute("sucesso", "Produto deletado com sucesso!");
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao deletar produto: " + e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/401")
    public String unauthorized() {
        return "error/401";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "error/403";
    }

}