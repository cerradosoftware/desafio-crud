package com.desafio.crud.desafiocrud.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hello")
public class HelloController {

    @RequestMapping("/")
    public ResponseEntity<String> index(){

        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }

}

