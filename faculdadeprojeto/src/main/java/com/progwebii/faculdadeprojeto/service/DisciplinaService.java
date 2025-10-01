package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.CursoDTO;
import com.progwebii.faculdadeprojeto.dto.DisciplinaDTO;
import com.progwebii.faculdadeprojeto.model.Curso;
import com.progwebii.faculdadeprojeto.model.Disciplina;
import com.progwebii.faculdadeprojeto.repository.DisciplinaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public Disciplina cadastrar(DisciplinaDTO disciplinaDTO) {
        Disciplina disciplinaSalvo = new Disciplina();
        disciplinaSalvo.setDisciplinaNome(disciplinaDTO.getDisciplinaNome());
        return disciplinaRepository.save(disciplinaSalvo);
    }

    public List<Disciplina> listar() {
        return disciplinaRepository.findAll();
    }

    public Disciplina atualizar(Long id, DisciplinaDTO disciplinaDTO) {
        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com id: " + id));

        disciplinaExistente.setDisciplinaNome(disciplinaDTO.getDisciplinaNome());
        return disciplinaRepository.save(disciplinaExistente);
    }

    public void deletar(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Disciplina não encontrada com id: " + id));
        disciplinaRepository.deleteById(id);
    }
}
