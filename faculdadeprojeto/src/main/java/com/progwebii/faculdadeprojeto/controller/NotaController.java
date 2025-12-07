package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.NotaDTO;
import com.progwebii.faculdadeprojeto.model.Nota;
import com.progwebii.faculdadeprojeto.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping
    public ResponseEntity<Nota> salvar(@RequestBody NotaDTO dto) {
        Nota salva = notaService.salvarOuAtualizar(dto);
        return ResponseEntity.ok(salva);
    }

    @GetMapping("/disciplina/{disciplinaId}")
    public ResponseEntity<List<Nota>> buscarPorDisciplina(@PathVariable Long disciplinaId) {
        return ResponseEntity.ok(notaService.buscarPorDisciplina(disciplinaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        notaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
