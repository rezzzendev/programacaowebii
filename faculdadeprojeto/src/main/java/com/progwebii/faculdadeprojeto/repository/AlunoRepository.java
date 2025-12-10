package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
    Optional<Aluno> findByMatricula(String matricula);
    Optional<Aluno> findByMatriculaAndDeletedAtLessThanEqual(String matricula, LocalDateTime dataDelecao);
    List<Aluno> findByNomeContainingIgnoreCase(String nome);
    List<Aluno> findAllByDeletedAtLessThanEqualOrDeletedAtIsNull(LocalDateTime dataDelecao);

}
