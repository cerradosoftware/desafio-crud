package com.desafio.crud.desafiocrud.controllers;

import com.desafio.crud.desafiocrud.model.Cliente;
import com.desafio.crud.desafiocrud.repositories.ClienteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    Logger logger = LoggerFactory.getLogger(AcessoController.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Cliente> Get() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(name + " - LISTA CLIENTES - " + LocalDateTime.now());

        return clienteRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> GetById(@PathVariable(value = "id") long id)
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(name + " - BUSCA CLIENTE ID:"+ id  + " - " + LocalDateTime.now());

        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent())
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/save", method =  RequestMethod.POST)
    public Cliente Post(@Valid @RequestBody Cliente cliente)
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(name + " - SALVA CLIENTE" + LocalDateTime.now());
        return clienteRepository.save(cliente);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/update/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Cliente> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Cliente newCliente)
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(name + " - UPDATE CLIENTE ID:"+ id  + " - " + LocalDateTime.now());

        Optional<Cliente> oldCliente = clienteRepository.findById(id);
        if(oldCliente.isPresent()){
            Cliente cliente = oldCliente.get();
            cliente.setNome(newCliente.getNome());
            clienteRepository.save(cliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(name + " - DELETE CLIENTE ID:"+ id  + " - " + LocalDateTime.now());

        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            clienteRepository.delete(cliente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
