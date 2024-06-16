package com.estudos.vendas.service;

import com.estudos.vendas.entity.Cliente;

import java.util.List;

public interface ClienteServico {

    Cliente getClienteById(Integer id);

    Cliente save(Cliente cliente);

    void delete(Integer id);

    void update(Integer id, Cliente cliente);

    List<Cliente> find(Cliente filtro);

}
