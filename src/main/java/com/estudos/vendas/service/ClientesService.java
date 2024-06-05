package com.estudos.vendas.service;

import com.estudos.vendas.model.Cliente;
import com.estudos.vendas.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        clientesRepository.persistir(cliente);
    }

    private void validarCliente(Cliente cliente) {
    }

}
