package com.progwebii.faculdadeprojeto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double bimestre1;
    private Double bimestre2;
    private Double media;

    @ManyToOne
    @JoinColumn(name = "matricula_aluno")
    private Aluno aluno;

    @ManyToOne
    private Disciplina disciplina;
}

