package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.AlunoDTO;
import com.progwebii.faculdadeprojeto.dto.AlunoDisciplinaDTO;
import com.progwebii.faculdadeprojeto.model.*;
import com.progwebii.faculdadeprojeto.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunoDisciplinaRepository alunoDisciplinaRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    @Autowired
    private ProvaRepository provaRepository;


    public Aluno cadastrar(AlunoDTO dto) {

        // Gerar matrícula automática caso não venha preenchida
        if (dto.getMatricula() == null || dto.getMatricula().isBlank()) {
            String timestamp = java.time.LocalDateTime.now()
                    .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            dto.setMatricula(timestamp);
        }

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        Aluno aluno = new Aluno();
        aluno.setMatricula(dto.getMatricula());
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setCurso(curso);

        Aluno salvo = alunoRepository.save(aluno);

        if (dto.getDisciplinasIds() != null) {
            for (Long idDisciplina : dto.getDisciplinasIds()) {

                Disciplina disciplina = disciplinaRepository.findById(idDisciplina)
                        .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

                AlunoDisciplina ad = new AlunoDisciplina();
                ad.setAluno(salvo);
                ad.setDisciplina(disciplina);

                alunoDisciplinaRepository.save(ad);
            }
        }

        return salvo;
    }


    public void deletar(String matricula) {
        Aluno aluno = alunoRepository.findById(matricula
        ).orElseThrow(() -> new RuntimeException("Não foi encontrado aluno com a matricula" + matricula));
        aluno.setDeletedAt(LocalDateTime.now());
        alunoRepository.save(aluno);
    }


    public List<Aluno> listar() {
        return alunoRepository.findAllByDeletedAtLessThanEqualOrDeletedAtIsNull(LocalDateTime.now());
    }

    public Aluno buscarPorMatricula(String matricula) {
        return alunoRepository.findById(matricula)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }


    public Aluno atualizaAluno(AlunoDTO dto) {

        Aluno aluno = buscarPorMatricula(dto.getMatricula());

        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setDataNascimento(dto.getDataNascimento());


        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));




        List<AlunoDisciplina> alunoDisc = new ArrayList<>();
        dto.getDisciplinasIds().forEach(
                it -> {
                    Disciplina d = disciplinaRepository.findById(it)
                            .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
                    AlunoDisciplina ad = new AlunoDisciplina();
                    ad.setAluno(aluno);
                    ad.setDisciplina(d);
                    alunoDisc.add(ad);
                }
        );


        Aluno salvo = alunoDisciplinaRepository
                .saveAll(alunoDisc)
                .stream()
                .findFirst()
                .get().getAluno();

        return salvo;
    }

    // Painel do aluno
    public List<Disciplina> buscarDisciplinasDoAluno(String matricula) {
        return alunoDisciplinaRepository.findByAluno_Matricula(matricula)
                .stream()
                .map(AlunoDisciplina::getDisciplina)
                .toList();
    }

    public AlunoDisciplinaDTO buscarDetalhesDisciplina(String matricula, Long disciplinaId) {

        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Nota nota = notaRepository.findByAlunoMatriculaAndDisciplinaId(matricula, disciplinaId)
                .orElse(null);

        Frequencia freq = frequenciaRepository.findByAlunoMatriculaAndDisciplinaId(matricula, disciplinaId)
                .orElse(null);

        List<Prova> provas = provaRepository.findByDisciplinaId(disciplinaId);

        AlunoDisciplinaDTO dto = new AlunoDisciplinaDTO();
        dto.setNome(disciplina.getNome());

        double nota1 = nota != null ? nota.getBimestre1() : 0;
        double nota2 = nota != null ? nota.getBimestre2() : 0;
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

        dto.setHorariosProvas(
                provas.stream().map(p -> p.getDataHora().toString()).toList()
        );

        return dto;
    }
}
