package com.desafio.crud.desafiocrud.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TELEFONES")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Tipo nao pode ser nulo")
    @Enumerated(EnumType.STRING)
    private String tipo;
    @NotNull(message = "Numero nao pode ser nulo")
    private String numero;
}
