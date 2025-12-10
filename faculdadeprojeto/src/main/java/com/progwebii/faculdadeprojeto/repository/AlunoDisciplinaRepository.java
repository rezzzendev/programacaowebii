package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.AlunoDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoDisciplinaRepository extends JpaRepository<AlunoDisciplina, Long> {

    List<AlunoDisciplina> findByAluno_Matricula(String matricula);
    void deleteByAlunoMatricula(String matricula);
}
