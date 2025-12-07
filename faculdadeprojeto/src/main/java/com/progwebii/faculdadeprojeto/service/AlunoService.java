package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.AlunoDTO;
import com.progwebii.faculdadeprojeto.dto.AlunoDisciplinaDTO;
import com.progwebii.faculdadeprojeto.model.*;
// Novos imports para a funcionalidade do aluno
import com.progwebii.faculdadeprojeto.repository.*;
// Fim dos novos imports

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    @Autowired
    private ProvaRepository provaRepository;


    public Aluno cadastrar(AlunoDTO dto) {
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        Aluno aluno = new Aluno();
        aluno.setMatricula(dto.getMatricula());
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setCurso(curso);

        return alunoRepository.save(aluno);
    }

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorMatricula(String matricula) {
        return alunoRepository.findById(matricula)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    public Aluno atualizar(String matricula, AlunoDTO dto) {
        Aluno aluno = buscarPorMatricula(matricula);

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setCurso(curso);

        return alunoRepository.save(aluno);
    }

    public void deletar(String matricula) {
        alunoRepository.deleteById(matricula);
    }

    // =======================================================
    // ✅ NOVOS MÉTODOS DO PAINEL (SEM AlunoDisciplina)
    // =======================================================

    public List<Disciplina> buscarDisciplinasDoAluno(String matricula) {
        return notaRepository.findByAlunoMatricula(matricula).stream()
                .map(n -> n.getDisciplina())
                .distinct()
                .toList();
    }

    public AlunoDisciplinaDTO buscarDetalhesDisciplina(String matricula, Long disciplinaId) {


        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));


        Nota nota = notaRepository
                .findByAlunoMatriculaAndDisciplinaId(matricula, disciplinaId)
                .orElse(null);


        Frequencia freq = frequenciaRepository
                .findByAlunoMatriculaAndDisciplinaId(matricula, disciplinaId)
                .orElse(null);


        List<Prova> provas = provaRepository.findByDisciplinaId(disciplinaId);


        AlunoDisciplinaDTO dto = new AlunoDisciplinaDTO();
        dto.setNome(disciplina.getNome());

        double nota1 = nota != null ? nota.getBimestre1() : 0.0;
        double nota2 = nota != null ? nota.getBimestre2() : 0.0;
        double media = nota != null ? nota.getMedia() : (nota1 + nota2) / 2;

        dto.setNota1(nota1);
        dto.setNota2(nota2);
        dto.setMedia(media);
        dto.setNotaNecessaria(Math.max(0, 6 - media));

        if (freq != null) {
            dto.setFaltas(freq.getFaltas());
            dto.setPresencas(freq.getPresencas());
            dto.setFaltasRestantes(25 - freq.getFaltas());
        } else {
            dto.setFaltas(0);
            dto.setPresencas(0);
            dto.setFaltasRestantes(25);
        }

        List<String> datas = provas.stream()
                .map(p -> p.getDataHora().toString())
                .toList();

        dto.setHorariosProvas(datas);

        return dto;
    }


}
