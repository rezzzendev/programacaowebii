package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
}
