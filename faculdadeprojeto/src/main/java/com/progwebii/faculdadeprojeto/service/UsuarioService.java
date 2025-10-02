package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


}
