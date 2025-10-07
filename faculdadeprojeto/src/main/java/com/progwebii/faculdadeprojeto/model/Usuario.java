package com.progwebii.faculdadeprojeto.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@Table(name = "tb_usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nome_usuario", nullable = false)
    private String usuarioNome;

    @Column(name = "status")
    private Boolean status;
}
