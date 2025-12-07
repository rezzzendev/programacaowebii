package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Frequencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {

    Optional<Frequencia> findByAlunoMatriculaAndDisciplinaId(String matricula, Long disciplinaId);
}