package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.AlunoDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoDisciplinaRepository extends JpaRepository<AlunoDisciplina, Long> {

    // Método que busca todas as entidades AlunoDisciplina por matrícula do aluno
    List<AlunoDisciplina> findByAlunoMatricula(String matricula);

    // Método que busca uma entidade específica por matrícula e ID da disciplina
    Optional<AlunoDisciplina> findByAlunoMatriculaAndDisciplinaId(String matricula, Long disciplinaId);
}