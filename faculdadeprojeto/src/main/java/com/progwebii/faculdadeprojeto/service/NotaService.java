package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.NotaDTO;
import com.progwebii.faculdadeprojeto.model.Aluno;
import com.progwebii.faculdadeprojeto.model.Disciplina;
import com.progwebii.faculdadeprojeto.model.Nota;
import com.progwebii.faculdadeprojeto.repository.AlunoRepository;
import com.progwebii.faculdadeprojeto.repository.DisciplinaRepository;
import com.progwebii.faculdadeprojeto.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    // MÉTODO PRINCIPAL: cadastra ou atualiza (UPSERT)
    public Nota salvarOuAtualizar(NotaDTO dto) {
        Aluno aluno = alunoRepository.findByMatricula(dto.getMatriculaAluno())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado: " + dto.getMatriculaAluno()));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));


        Optional<Nota> notaExistente = notaRepository
                .findByAlunoMatriculaAndDisciplinaId(dto.getMatriculaAluno(), dto.getDisciplinaId());

        Nota nota;

        if (notaExistente.isPresent()) {
            nota = notaExistente.get();
        } else {
            nota = new Nota();
            nota.setAluno(aluno);
            nota.setDisciplina(disciplina);
        }

        nota.setBimestre1(dto.getBimestre1() != null ? dto.getBimestre1() : 0.0);
        nota.setBimestre2(dto.getBimestre2() != null ? dto.getBimestre2() : 0.0);
        nota.setMedia((nota.getBimestre1() + nota.getBimestre2()) / 2);

        return notaRepository.save(nota);
    }

    public List<Nota> buscarPorDisciplina(Long disciplinaId) {
        return notaRepository.findByDisciplinaId(disciplinaId);
    }

    public void deletar(Long id) {
        notaRepository.deleteById(id);
    }
}