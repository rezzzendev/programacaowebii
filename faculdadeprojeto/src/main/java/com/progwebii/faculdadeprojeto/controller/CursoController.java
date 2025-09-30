package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.AlunoDTO;
import com.progwebii.faculdadeprojeto.dto.CursoDTO;
import com.progwebii.faculdadeprojeto.model.Aluno;
import com.progwebii.faculdadeprojeto.model.Curso;
import com.progwebii.faculdadeprojeto.service.CursoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@AllArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Curso> cadastrar(@RequestBody CursoDTO cursoDTO) {
        Curso cursoCadastrado = cursoService.cadastrar(cursoDTO);
        return ResponseEntity.ok(cursoCadastrado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Curso>> listar() {
        List<Curso> cursos = cursoService.listar();
        return ResponseEntity.ok(cursos);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
        return ResponseEntity.ok(cursoService.atualizar(id, cursoDTO));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Curso> deletar(@PathVariable Long id) {
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
