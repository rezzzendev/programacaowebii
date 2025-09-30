package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
