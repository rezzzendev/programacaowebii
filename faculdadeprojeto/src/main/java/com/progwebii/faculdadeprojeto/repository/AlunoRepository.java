package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
