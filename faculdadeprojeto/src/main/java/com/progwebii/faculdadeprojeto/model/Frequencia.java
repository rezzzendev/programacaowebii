package com.progwebii.faculdadeprojeto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_frequencia")
public class Frequencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer faltas;
    private Integer presencas;

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Disciplina disciplina;
}