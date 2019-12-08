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
    @OneToOne(cascade=CascadeType.ALL)
    private Endereco endereco;

    @NotNull (message = "Ao menos um telefone deve ser informado")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="clientes_id")
    private List<Telefone> telefones;

    @NotNull (message = "Ao menos um email deve ser informado")
    @Column(name = "EMAIL")
    @ElementCollection
    private List<String> emails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }


}
