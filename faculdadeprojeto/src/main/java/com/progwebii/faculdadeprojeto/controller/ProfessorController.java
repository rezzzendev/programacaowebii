package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.CursoDTO;
import com.progwebii.faculdadeprojeto.dto.ProfessorDTO;
import com.progwebii.faculdadeprojeto.model.Curso;
import com.progwebii.faculdadeprojeto.model.Professor;
import com.progwebii.faculdadeprojeto.service.CursoService;
import com.progwebii.faculdadeprojeto.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@AllArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Professor> cadastrar(@RequestBody ProfessorDTO professorDTO) {
        Professor professorCadastrado = professorService.cadastrar(professorDTO);
        return ResponseEntity.ok(professorCadastrado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Professor>> listar() {
        List<Professor> professores = professorService.listar();
        return ResponseEntity.ok(professores);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
        return ResponseEntity.ok(professorService.atualizar(id, professorDTO));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Professor> deletar(@PathVariable Long id) {
        professorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
