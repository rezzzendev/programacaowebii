package com.progwebii.faculdadeprojeto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_aluno")
@Data
public class Aluno {

    @Id
    @Column(nullable = false, unique = true)
    private String matricula;

    private String nome;

    private String email;

    private LocalDate dataNascimento;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne()
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
