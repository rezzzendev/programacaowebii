package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.ProvaDTO;
import com.progwebii.faculdadeprojeto.model.Prova;
import com.progwebii.faculdadeprojeto.service.ProvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provas")
@CrossOrigin(origins = "*")
public class ProvaController {

    @Autowired
    private ProvaService provaService;


    @PostMapping
    public ResponseEntity<Prova> cadastrar(@RequestBody ProvaDTO dto) {
        Prova prova = provaService.cadastrar(dto);
        return ResponseEntity.status(201).body(prova);
    }


    @GetMapping("/disciplina/{id}")
    public List<Prova> listarPorDisciplina(@PathVariable Long id) {
        return provaService.listarPorDisciplina(id);
    }


    @PutMapping("/{id}")
    public Prova atualizar(@PathVariable Long id, @RequestBody Prova prova) {
        return provaService.atualizar(id, prova);
    }


    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        provaService.excluir(id);
    }
}
