package com.progwebii.faculdadeprojeto.controller;

import com.progwebii.faculdadeprojeto.model.Usuario;
import com.progwebii.faculdadeprojeto.service.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@Controller
public class AuthController {

    private final UserDetailsServiceImpl userService;

    public AuthController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute Usuario usuario, Model model) {
        try {

            if (!validarCamposObrigatorios(usuario)) {
                model.addAttribute("erro", "Preencha todos os campos obrigatórios!");
                model.addAttribute("usuario", usuario);
                return "register";
            }


            userService.salvarUsuario(
                    usuario.getLogin(),
                    usuario.getSenha(),
                    usuario.getUsuarioNome(),
                    usuario.getEmail()
            );

            model.addAttribute("mensagem", "Usuário cadastrado com sucesso!");
            return "redirect:/login";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao registrar usuário: " + e.getMessage());
            model.addAttribute("usuario", usuario);
            return "register";
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }


    private boolean validarCamposObrigatorios(Object obj) throws IllegalAccessException {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);

            if (f.getName().equals("id") || f.getName().equals("status"))
                continue;

            Object valor = f.get(obj);
            if (valor == null || valor.toString().isBlank()) {
                System.out.println("Campo vazio: " + f.getName());
                return false;
            }
        }
        return true;
    }
}
