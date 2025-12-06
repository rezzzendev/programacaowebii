package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.ProfessorDTO;
import com.progwebii.faculdadeprojeto.model.Professor;
import com.progwebii.faculdadeprojeto.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Professor> cadastrar(@RequestBody ProfessorDTO professorDTO) {
        Professor professorCadastrado = professorService.cadastrar(professorDTO);
        return ResponseEntity.status(201).body(professorCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listar() {
        return ResponseEntity.ok(professorService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
        return ResponseEntity.ok(professorService.atualizar(id, professorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        professorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
