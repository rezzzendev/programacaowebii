package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.FrequenciaDTO;
import com.progwebii.faculdadeprojeto.model.Frequencia;
import com.progwebii.faculdadeprojeto.service.FrequenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/frequencias")
@CrossOrigin(origins = "http://localhost:4200")
public class FrequenciaController {

    @Autowired
    private FrequenciaService frequenciaService;

    @PostMapping
    public ResponseEntity<Frequencia> registrar(@RequestBody FrequenciaDTO dto) {
        Frequencia salva = frequenciaService.registrarFrequencia(dto);
        return ResponseEntity.ok(salva);
    }


    @GetMapping("/aluno/{matricula}/disciplina/{disciplinaId}")
    public ResponseEntity<Frequencia> buscarPorAlunoEDisciplina(
            @PathVariable String matricula,
            @PathVariable Long disciplinaId) {

        Frequencia frequencia = frequenciaService.buscarPorAlunoEDisciplina(matricula, disciplinaId);
        return ResponseEntity.ok(frequencia);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        frequenciaService.excluirFrequencia(id);
        return ResponseEntity.noContent().build();
    }
}