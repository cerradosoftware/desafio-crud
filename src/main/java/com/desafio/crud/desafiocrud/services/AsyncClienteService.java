package com.desafio.crud.desafiocrud.services;

import com.desafio.crud.desafiocrud.model.Cliente;
import com.desafio.crud.desafiocrud.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public CompletableFuture<Iterable<Cliente>> listClientes() {

        Iterable<Cliente> clientes = clienteRepository.findAll();

        return CompletableFuture.completedFuture(clientes);

    }

    public CompletableFuture<Cliente> find(long id){

        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent())
            return CompletableFuture.completedFuture(cliente.get());
        else
            return CompletableFuture.completedFuture(new Cliente());
    }

    public CompletableFuture<Cliente> save(Cliente cliente){

        Cliente saved = clienteRepository.save(cliente);

        return CompletableFuture.completedFuture(saved);
    }

    public CompletableFuture<Cliente> update(Cliente uCliente, long id){

        Optional<Cliente> oldCliente = clienteRepository.findById(id);
        if(oldCliente.isPresent()){
            Cliente cliente = oldCliente.get();
            cliente.setNome(uCliente.getNome());
            cliente.setCpf(uCliente.getCpf());
            cliente.setEndereco(uCliente.getEndereco());
            cliente.setEmails(uCliente.getEmails());
            cliente.setTelefones(uCliente.getTelefones());
            clienteRepository.save(cliente);
        }

        return CompletableFuture.completedFuture(uCliente);

    }

    public CompletableFuture<ResponseEntity> delete(long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            clienteRepository.delete(cliente.get());
            return CompletableFuture.completedFuture(new ResponseEntity(HttpStatus.OK));
        }
        else
            return CompletableFuture.completedFuture(new ResponseEntity(HttpStatus.NOT_FOUND));
    }





}
