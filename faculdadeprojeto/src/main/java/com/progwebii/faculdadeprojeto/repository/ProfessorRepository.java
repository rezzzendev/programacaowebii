package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
