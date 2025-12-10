package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.model.TipoUsuario;
import com.progwebii.faculdadeprojeto.model.Usuario;
import com.progwebii.faculdadeprojeto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new User(
                usuario.getLogin(),
                usuario.getSenha(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getTipo().name()))
        );
    }


    public void salvarUsuario(String login, String senha, String nome, String email) {

        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(passwordEncoder.encode(senha));

        
        usuario.setTipo(TipoUsuario.ALUNO);


        usuario.setMatriculaVinculada(null);

        usuarioRepository.save(usuario);
    }
}
