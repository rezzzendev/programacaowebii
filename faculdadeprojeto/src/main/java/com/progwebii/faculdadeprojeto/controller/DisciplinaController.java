package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.DisciplinaDTO;
import com.progwebii.faculdadeprojeto.dto.ProfessorDTO;
import com.progwebii.faculdadeprojeto.model.Disciplina;
import com.progwebii.faculdadeprojeto.model.Professor;
import com.progwebii.faculdadeprojeto.service.DisciplinaService;
import com.progwebii.faculdadeprojeto.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/disciplinas")
@AllArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Disciplina> cadastrar(@RequestBody DisciplinaDTO disciplinaDTO) {
        Disciplina disciplinaCadastrada = disciplinaService.cadastrar(disciplinaDTO);
        return ResponseEntity.ok(disciplinaCadastrada);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Disciplina>> listar() {
        List<Disciplina> disciplinas = disciplinaService.listar();
        return ResponseEntity.ok(disciplinas);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Disciplina> atualizar(@PathVariable Long id, @RequestBody DisciplinaDTO disciplinaDTO) {
        return ResponseEntity.ok(disciplinaService.atualizar(id, disciplinaDTO));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Disciplina> deletar(@PathVariable Long id) {
        disciplinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
