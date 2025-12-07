package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long> {

    List<Prova> findByDisciplinaId(Long disciplinaId);

}
