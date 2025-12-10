package com.progwebii.faculdadeprojeto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AlunoDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Disciplina disciplina;
}
