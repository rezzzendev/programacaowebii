package com.progwebii.faculdadeprojeto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    @Column(name = "curso_nome", nullable = false)
    private String cursoNome;

    @Column(name = "curso_duracao", nullable = false)
    private Integer cursoDuracao;
}
