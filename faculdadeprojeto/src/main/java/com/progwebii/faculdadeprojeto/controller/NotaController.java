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
    public ResponseEntity<Nota> cadastrar(@RequestBody NotaDTO notaDTO) {
        Nota notaCadastrada = notaService.cadastrar(notaDTO);
        return ResponseEntity.status(201).body(notaCadastrada);
    }

    @GetMapping
    public ResponseEntity<List<Nota>> listar() {
        return ResponseEntity.ok(notaService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> atualizar(@PathVariable Long id, @RequestBody NotaDTO notaDTO) {
        return ResponseEntity.ok(notaService.atualizar(id, notaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        notaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
