package com.example.biblioteca.service;

import com.example.biblioteca.model.Autor;
import com.example.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public void excluir(Long id) {
        autorRepository.deleteById(id);
    }
}
