package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.CursoDTO;
import com.progwebii.faculdadeprojeto.dto.ProfessorDTO;
import com.progwebii.faculdadeprojeto.model.Curso;
import com.progwebii.faculdadeprojeto.model.Professor;
import com.progwebii.faculdadeprojeto.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public Professor cadastrar(ProfessorDTO professorDTO) {
        Professor professorSalvo = new Professor();
        professorSalvo.setProfessorNome(professorDTO.getProfessorNome());
        return professorRepository.save(professorSalvo);
    }

    public List<Professor> listar() {
        return professorRepository.findAll();
    }

    public Professor atualizar(Long id, ProfessorDTO professorDTO) {
        Professor professorExistente = professorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado com id: " + id));

        professorExistente.setProfessorNome(professorDTO.getProfessorNome());
        return professorRepository.save(professorExistente);
    }

    public void deletar(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Professor não encontrado com id: " + id));
        professorRepository.deleteById(id);
    }
}
