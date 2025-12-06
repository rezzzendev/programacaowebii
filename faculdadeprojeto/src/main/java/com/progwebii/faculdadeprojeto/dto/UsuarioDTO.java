package com.progwebii.faculdadeprojeto.dto;

import lombok.Data;


@Data
public class UsuarioDTO {

    private Long id;
    private String login;
    private String senha;

    private String usuarioNome;

    private String email;
}

