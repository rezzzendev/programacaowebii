package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.AlunoDTO;
import com.progwebii.faculdadeprojeto.model.Aluno;
import com.progwebii.faculdadeprojeto.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@AllArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Aluno> cadastrar(@RequestBody AlunoDTO alunoDTO) {
        Aluno alunoCadastrado = alunoService.cadastrar(alunoDTO);
        return ResponseEntity.ok(alunoCadastrado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Aluno>> listar() {
        List<Aluno> alunos = alunoService.listar();
        return ResponseEntity.ok(alunos);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
        return ResponseEntity.ok(alunoService.atualizar(id, alunoDTO));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Aluno> deletar(@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
