package com.desafio.crud.desafiocrud.model;

public enum TiposTelefone {

    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial"),
    CELULAR("Celular");

    private String descricao;

    TiposTelefone(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
