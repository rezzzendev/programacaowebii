package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByAlunoMatricula(String matricula);
}