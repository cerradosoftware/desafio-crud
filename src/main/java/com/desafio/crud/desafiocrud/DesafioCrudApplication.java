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
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@EnableAsync
@SpringBootApplication
public class DesafioCrudApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioCrudApplication.class, args);
	}

	@Bean(name = "asyncExecutor")
	public Executor asyncExecutor()
	{
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(3);
		executor.setMaxPoolSize(3);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("AsynchThread-");
		executor.initialize();
		return executor;
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
