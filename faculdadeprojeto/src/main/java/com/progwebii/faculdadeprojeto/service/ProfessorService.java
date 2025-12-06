package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.ProfessorDTO;
import com.progwebii.faculdadeprojeto.model.Professor;
import com.progwebii.faculdadeprojeto.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor cadastrar(ProfessorDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        return professorRepository.save(professor);
    }

    public List<Professor> listar() {
        return professorRepository.findAll();
    }

    public Professor atualizar(Long id, ProfessorDTO dto) {
        Professor existente = professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        existente.setNome(dto.getNome());
        existente.setEmail(dto.getEmail());
        return professorRepository.save(existente);
    }

    public void deletar(Long id) {
        professorRepository.deleteById(id);
    }
}
