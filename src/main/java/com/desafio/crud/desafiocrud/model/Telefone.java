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
    @Enumerated(EnumType.ORDINAL)
    private TiposTelefone tipo;
    @NotNull(message = "Numero nao pode ser nulo")
    private String numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TiposTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TiposTelefone tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
