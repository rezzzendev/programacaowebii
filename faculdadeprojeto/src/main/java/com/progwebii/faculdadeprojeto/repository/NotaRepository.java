package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota, Long> {


    List<Nota> findByAlunoMatricula(String matricula);


    Optional<Nota> findByAlunoMatriculaAndDisciplinaId(String matricula, Long disciplinaId);


    List<Nota> findByDisciplinaId(Long disciplinaId);
}
