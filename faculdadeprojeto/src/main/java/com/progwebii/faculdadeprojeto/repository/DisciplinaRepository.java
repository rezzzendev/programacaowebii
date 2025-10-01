package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
