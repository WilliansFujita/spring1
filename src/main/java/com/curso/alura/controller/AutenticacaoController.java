package com.curso.alura.controller;

import com.curso.alura.domain.usuario.DadosAutenticacao;
import com.curso.alura.domain.usuario.Usuario;
import com.curso.alura.infra.security.DadosToken;
import com.curso.alura.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid DadosAutenticacao dadosAutenticacao){
        var autenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var authenticate = authenticationManager.authenticate(autenticationToken);
        var token = tokenService.gerarToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok(new DadosToken(token));
    }
}
