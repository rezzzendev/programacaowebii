package com.progwebii.faculdadeprojeto.repository;

import com.progwebii.faculdadeprojeto.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    // ✅ BUSCAR TODAS AS NOTAS DE UM ALUNO (para listar disciplinas)
    List<Nota> findByAlunoMatricula(String matricula);

    // ✅ BUSCAR NOTA DO ALUNO EM UMA DISCIPLINA ESPECÍFICA (card do aluno)
    Optional<Nota> findByAlunoMatriculaAndDisciplinaId(String matricula, Long disciplinaId);

    // ✅ Usado para outras telas (professor, etc)
    List<Nota> findByDisciplinaId(Long disciplinaId);
}
