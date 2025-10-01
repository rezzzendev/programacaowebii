package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
}
