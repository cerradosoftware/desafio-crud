package com.desafio.crud.desafiocrud.controllers;

import com.desafio.crud.desafiocrud.model.LoggedUser;
import com.desafio.crud.desafiocrud.model.User;
import com.desafio.crud.desafiocrud.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/v1/acesso")
public class AcessoController {
    Logger logger = LoggerFactory.getLogger(AcessoController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @RequestMapping(value = "/login", method = RequestMethod.POST )
    public ResponseEntity<LoggedUser> login(@Valid @RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

            String jwt = jwtProvider.createToken(user.getLogin());

            LoggedUser loggedUser = new LoggedUser();
            loggedUser.setLogin(authentication.getName());
            loggedUser.setJwt(jwt);
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)  authentication.getAuthorities();
            SimpleGrantedAuthority authority = authorities.stream().findFirst().get();
            loggedUser.setRole(authority.toString());

            logger.info(user.getLogin() + " - LOGIN - " + LocalDateTime.now());

            return new ResponseEntity<>(loggedUser, HttpStatus.OK);
        } catch (AuthenticationException e){
            System.out.println("Erro ao entrar com, " + user.getLogin());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }



}
