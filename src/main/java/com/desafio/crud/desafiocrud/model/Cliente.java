package com.desafio.crud.desafiocrud.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "CLIENTES")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Nome nao pode ser nulo")
    @Column(length = 100)
    private String nome;
    @NotNull(message = "cpf nao pode ser nulo")
    @Column(length = 11)
    private String cpf;
    @NotNull (message = "Endereco nao pode ser nulo")
    @OneToOne
    private Endereco endereco;
    @NotNull (message = "Ao menos um telefone deve ser informado")
    @OneToMany
    private List<Telefone> telefones;
    @NotNull (message = "Ao menos um email deve ser informado")
    @OneToMany
    private List<String> emails;




}
