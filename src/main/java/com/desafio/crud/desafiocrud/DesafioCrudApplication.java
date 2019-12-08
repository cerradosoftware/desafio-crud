package com.desafio.crud.desafiocrud;

import com.desafio.crud.desafiocrud.model.Cliente;
import com.desafio.crud.desafiocrud.model.Endereco;
import com.desafio.crud.desafiocrud.model.Telefone;
import com.desafio.crud.desafiocrud.model.TiposTelefone;
import com.desafio.crud.desafiocrud.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DesafioCrudApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioCrudApplication.class, args);
	}

	@Override
	public void run(String... args ){

		saveCliente("Vinicius", "03927658103");
		saveCliente("Tauana", "04542167186");
	}

	private void saveCliente(String nome, String cpf) {
		Cliente cliente1 = new Cliente();
		cliente1.setNome(nome);
		cliente1.setCpf(cpf);

		List<Telefone> telefones = new ArrayList<>();
		Telefone telefone = new Telefone();
		telefone.setNumero("61982987442");
		telefone.setTipo(TiposTelefone.CELULAR);
		Telefone telefone2 = new Telefone();
		telefone2.setNumero("61982987442");
		telefone2.setTipo(TiposTelefone.COMERCIAL);

		telefones.add(telefone);
		telefones.add(telefone2);

		cliente1.setTelefones(telefones);

		Endereco endereco = new Endereco();
		endereco.setCep("71010105");
		endereco.setBairro("Guara");
		endereco.setCidade("Brasilia");
		endereco.setComplemento("Casa 21");
		endereco.setLogradouro("QI 8 COnj J");
		endereco.setUf("DF");

		cliente1.setEndereco(endereco);

		List<String> emails = new ArrayList<>();
		emails.add("teste@gmail.com");
		emails.add("teste2@gmail.com");

		cliente1.setEmails(emails);

		clienteRepository.save(cliente1);
	}

}
