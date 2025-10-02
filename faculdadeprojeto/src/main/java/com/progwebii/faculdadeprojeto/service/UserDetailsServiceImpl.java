package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.model.Usuario;
import com.progwebii.faculdadeprojeto.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UsuarioRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuarioNome(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(login);
        builder.password(usuario.getSenha());
        builder.roles("USUARIO");
        return builder.build();
    }

    public void salvarUsuario(String login, String senha) {
        usuarioRepository.save(new Usuario(login, passwordEncoder.encode(senha)));
    }
}
