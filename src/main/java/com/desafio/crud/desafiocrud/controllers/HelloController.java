package com.desafio.crud.desafiocrud.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hello")
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "Hello world!";
    }

}

