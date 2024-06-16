package com.estudos.vendas.controller;

import com.estudos.vendas.entity.Cliente;
import com.estudos.vendas.service.ClienteServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteServico clienteServico;

    @GetMapping(value = "/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clienteServico.getClienteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteServico.save(cliente);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clienteServico.delete(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        clienteServico.update(id, cliente);
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro) {
        return clienteServico.find(filtro);
    }

}
