package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.CursoDTO;
import com.progwebii.faculdadeprojeto.model.Curso;
import com.progwebii.faculdadeprojeto.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public Curso cadastrar(CursoDTO cursoDTO) {
        Curso cursoSalvo = new Curso();
        cursoSalvo.setCursoNome(cursoDTO.getCursoNome());
        cursoSalvo.setCursoDuracao(cursoDTO.getCursoDuracao());
        return cursoRepository.save(cursoSalvo);
    }

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public Curso atualizar(Long id, CursoDTO cursoDTO) {
        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com id: " + id));

        cursoExistente.setCursoNome(cursoDTO.getCursoNome());
        cursoExistente.setCursoDuracao(cursoDTO.getCursoDuracao());
        return cursoRepository.save(cursoExistente);
    }

    public void deletar(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Curso não encontrado com id: " + id));
        cursoRepository.deleteById(id);
    }
}
