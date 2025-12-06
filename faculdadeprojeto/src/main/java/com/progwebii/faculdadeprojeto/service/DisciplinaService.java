package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.DisciplinaDTO;
import com.progwebii.faculdadeprojeto.model.Curso;
import com.progwebii.faculdadeprojeto.model.Disciplina;
import com.progwebii.faculdadeprojeto.model.Professor;
import com.progwebii.faculdadeprojeto.repository.CursoRepository;
import com.progwebii.faculdadeprojeto.repository.DisciplinaRepository;
import com.progwebii.faculdadeprojeto.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public Disciplina cadastrar(DisciplinaDTO dto) {
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        Disciplina d = new Disciplina();
        d.setNome(dto.getNome());
        d.setCurso(curso);
        d.setProfessor(professor);

        return disciplinaRepository.save(d);
    }

    public List<Disciplina> listar() {
        return disciplinaRepository.findAll();
    }

    public Disciplina atualizar(Long id, DisciplinaDTO dto) {
        Disciplina existente = disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        existente.setNome(dto.getNome());

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        existente.setCurso(curso);

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        existente.setProfessor(professor);

        return disciplinaRepository.save(existente);
    }

    public void deletar(Long id) {
        disciplinaRepository.deleteById(id);
    }
}
