package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.CursoDTO;
import com.progwebii.faculdadeprojeto.model.Curso;
import com.progwebii.faculdadeprojeto.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso cadastrar(CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.getNome());
        return cursoRepository.save(curso);
    }

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public Curso atualizar(Long id, CursoDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        curso.setNome(dto.getNome());

        return cursoRepository.save(curso);
    }

    public void deletar(Long id) {
        cursoRepository.deleteById(id);
    }
}