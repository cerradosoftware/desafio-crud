package com.desafio.crud.desafiocrud.controllers;

import com.desafio.crud.desafiocrud.model.Cliente;
import com.desafio.crud.desafiocrud.repositories.ClienteRepository;

import com.desafio.crud.desafiocrud.services.AsyncClienteService;
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
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    Logger logger = LoggerFactory.getLogger(AcessoController.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AsyncClienteService asyncClienteService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public CompletableFuture
            <Iterable<Cliente>> Get() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(name + " - LISTA CLIENTES - " + LocalDateTime.now());

        return asyncClienteService.listClientes();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CompletableFuture<Cliente> GetById(@PathVariable(value = "id") long id)
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(name + " - BUSCA CLIENTE ID:"+ id  + " - " + LocalDateTime.now());

        return asyncClienteService.find(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/save", method =  RequestMethod.POST)
    public CompletableFuture<Cliente> Post(@Valid @RequestBody Cliente cliente)
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(name + " - SALVA CLIENTE" + LocalDateTime.now());
        return asyncClienteService.save(cliente);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/update/{id}", method =  RequestMethod.PUT)
    public CompletableFuture<Cliente> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Cliente newCliente)
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(name + " - UPDATE CLIENTE ID:"+ id  + " - " + LocalDateTime.now());

        return asyncClienteService.update(newCliente, id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity> Delete(@PathVariable(value = "id") long id)
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info(name + " - DELETE CLIENTE ID:"+ id  + " - " + LocalDateTime.now());

        return asyncClienteService.delete(id);
    }
}
