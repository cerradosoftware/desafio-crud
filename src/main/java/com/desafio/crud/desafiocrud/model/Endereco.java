package com.desafio.crud.desafiocrud.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "CEP nao pode ser nulo")
    @Column(length = 8)
    private String cep;
    @NotNull(message = "Logradouro nao pode ser nulo")
    private String logradouro;
    @NotNull(message = "Bairro nao pode ser nulo")
    private String bairro;
    private String complemento;
    @NotNull(message = "Cidade nao pode ser nulo")
    private String cidade;
    @NotNull(message = "Estado nao pode ser nulo")
    private String uf;

}
