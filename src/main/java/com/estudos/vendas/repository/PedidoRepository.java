package com.estudos.vendas.repository;

import com.estudos.vendas.entity.Cliente;
import com.estudos.vendas.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
