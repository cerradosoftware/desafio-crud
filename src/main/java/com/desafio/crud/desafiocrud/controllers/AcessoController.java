package com.desafio.crud.desafiocrud.controllers;

import com.desafio.crud.desafiocrud.model.User;
import com.desafio.crud.desafiocrud.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/acesso")
public class AcessoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @RequestMapping(value = "/login", method = RequestMethod.POST )
    public String login(@Valid @RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

            return jwtProvider.createToken(user.getLogin());
        } catch (AuthenticationException e){
            System.out.println("Erro ao entrar com, " + user.getLogin());
        }

        return "";
    }



}
