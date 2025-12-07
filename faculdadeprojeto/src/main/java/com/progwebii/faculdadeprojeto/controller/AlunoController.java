package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.AlunoDTO;
import com.progwebii.faculdadeprojeto.dto.AlunoDisciplinaDTO;
import com.progwebii.faculdadeprojeto.model.Aluno;
import com.progwebii.faculdadeprojeto.model.Disciplina;
import com.progwebii.faculdadeprojeto.model.AlunoDisciplina;


import com.progwebii.faculdadeprojeto.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@CrossOrigin(origins = "http://localhost:4200")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody AlunoDTO dto) {
        Aluno saved = alunoService.cadastrar(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listar() {
        return ResponseEntity.ok(alunoService.listar());
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> buscar(@PathVariable String matricula) {
        return ResponseEntity.ok(alunoService.buscarPorMatricula(matricula));
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Aluno> atualizar(@PathVariable String matricula, @RequestBody AlunoDTO dto) {
        return ResponseEntity.ok(alunoService.atualizar(matricula, dto));
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletar(@PathVariable String matricula) {
        alunoService.deletar(matricula);
        return ResponseEntity.noContent().build();
    }

    // ✅ PAINEL DO ALUNO
    @GetMapping("/{matricula}/disciplinas")
    public ResponseEntity<List<Disciplina>> buscarDisciplinas(@PathVariable String matricula) {
        return ResponseEntity.ok(alunoService.buscarDisciplinasDoAluno(matricula));
    }
    @GetMapping("/{matricula}/disciplinas/{disciplinaId}")
    public ResponseEntity<AlunoDisciplinaDTO> buscarDetalhesDisciplina(
            @PathVariable String matricula,
            @PathVariable Long disciplinaId) {

        AlunoDisciplinaDTO dto = alunoService.buscarDetalhesDisciplina(matricula, disciplinaId);
        return ResponseEntity.ok(dto);
    }
}
