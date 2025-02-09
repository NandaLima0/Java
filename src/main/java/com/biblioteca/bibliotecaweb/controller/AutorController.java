package com.example.biblioteca.controller;

import com.example.biblioteca.model.Autor;
import com.example.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public String listarAutores(Model model) {
        List<Autor> autores = autorService.listarTodos();
        model.addAttribute("autores", autores);
        return "autor-list";
    }

    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("autor", new Autor());
        return "autor-form";
    }

    @PostMapping
    public String salvarAutor(@ModelAttribute Autor autor) {
        autorService.salvar(autor);
        return "redirect:/autores";
    }

    @GetMapping("/editar/{id}")
    public String editarAutor(@PathVariable Long id, Model model) {
        Autor autor = autorService.buscarPorId(id);
        if (autor != null) {
            model.addAttribute("autor", autor);
            return "autor-form";
        }
        return "redirect:/autores";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAutor(@PathVariable Long id) {
        autorService.excluir(id);
        return "redirect:/autores";
    }
}