package com.example.biblioteca.controller;

import com.example.biblioteca.model.Livro;
import com.example.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public String listarLivros(Model model) {
        List<Livro> livros = livroService.listarTodos();
        model.addAttribute("livros", livros);
        return "livro-list";
    }

    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("livro", new Livro());
        return "livro-form";
    }

    @PostMapping
    public String salvarLivro(@ModelAttribute Livro livro) {
        livroService.salvar(livro);
        return "redirect:/livros";
    }

    @GetMapping("/editar/{id}")
    public String editarLivro(@PathVariable Long id, Model model) {
        Livro livro = livroService.buscarPorId(id);
        if (livro != null) {
            model.addAttribute("livro", livro);
            return "livro-form";
        }
        return "redirect:/livros";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id) {
        livroService.excluir(id);
        return "redirect:/livros";
    }
}
