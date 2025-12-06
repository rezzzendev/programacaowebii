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

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Nota cadastrar(NotaDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.getMatriculaAluno())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        Nota nota = new Nota();
        nota.setAluno(aluno);
        nota.setDisciplina(disciplina);
        nota.setBimestre1(dto.getBimestre1());
        nota.setBimestre2(dto.getBimestre2());

        Double b1 = dto.getBimestre1() == null ? 0.0 : dto.getBimestre1();
        Double b2 = dto.getBimestre2() == null ? 0.0 : dto.getBimestre2();
        nota.setMedia((b1 + b2) / 2);

        return notaRepository.save(nota);
    }

    public List<Nota> listar() {
        return notaRepository.findAll();
    }

    public List<Nota> buscarPorAluno(String matricula) {
        return notaRepository.findByAlunoMatricula(matricula);
    }

    public Nota atualizar(Long id, NotaDTO dto) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        nota.setBimestre1(dto.getBimestre1());
        nota.setBimestre2(dto.getBimestre2());

        Double b1 = dto.getBimestre1() == null ? 0.0 : dto.getBimestre1();
        Double b2 = dto.getBimestre2() == null ? 0.0 : dto.getBimestre2();
        nota.setMedia((b1 + b2) / 2);

        return notaRepository.save(nota);
    }

    public void deletar(Long id) {
        notaRepository.deleteById(id);
    }
}
