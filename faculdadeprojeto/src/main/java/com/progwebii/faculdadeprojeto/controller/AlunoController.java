package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.AlunoDTO;
import com.progwebii.faculdadeprojeto.dto.AlunoDisciplinaDTO;
import com.progwebii.faculdadeprojeto.model.Aluno;
import com.progwebii.faculdadeprojeto.model.Disciplina;
import com.progwebii.faculdadeprojeto.repository.AlunoDisciplinaRepository;
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

    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody AlunoDTO dto) {
        return ResponseEntity.status(201).body(alunoService.cadastrar(dto));
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
        return ResponseEntity.ok(alunoService.atualizaAluno(dto));
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletar(@PathVariable String matricula) {
        alunoService.deletar(matricula);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{matricula}/disciplinas-ids")
    public ResponseEntity<List<Long>> listarDisciplinasIds(@PathVariable String matricula) {
        List<Long> ids = alunoDisciplinaRepository.findByAluno_Matricula(matricula)
                .stream()
                .map(ad -> ad.getDisciplina().getId())
                .toList();

        return ResponseEntity.ok(ids);
    }

    @GetMapping("/{matricula}/disciplinas")
    public ResponseEntity<List<Disciplina>> listarDisciplinas(@PathVariable String matricula) {
        return ResponseEntity.ok(alunoService.buscarDisciplinasDoAluno(matricula));
    }

    @GetMapping("/{matricula}/disciplinas/{disciplinaId}")
    public ResponseEntity<AlunoDisciplinaDTO> detalhesDisciplina(
            @PathVariable String matricula,
            @PathVariable Long disciplinaId
    ) {
        return ResponseEntity.ok(alunoService.buscarDetalhesDisciplina(matricula, disciplinaId));
    }
}
