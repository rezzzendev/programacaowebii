package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.dto.NotaDTO;
import com.progwebii.faculdadeprojeto.model.Nota;
import com.progwebii.faculdadeprojeto.service.NotaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/notas")
@AllArgsConstructor
public class NotaController {

    private NotaService notaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Nota> cadastrar(@RequestBody NotaDTO notaDTO) {
        Nota notaCadastrada = notaService.cadastrar(notaDTO);
        return ResponseEntity.ok(notaCadastrada);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Nota>> listar() {
        List<Nota> notas = notaService.listar();
        return ResponseEntity.ok(notas);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Nota> atualizar(@PathVariable Long id, @RequestBody NotaDTO notaDTO) {
        return ResponseEntity.ok(notaService.atualizar(id, notaDTO));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Nota> deletar(@PathVariable Long id) {
        notaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
