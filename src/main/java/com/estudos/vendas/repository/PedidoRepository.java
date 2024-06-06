package com.estudos.vendas.repository;

import com.estudos.vendas.domain.Cliente;
import com.estudos.vendas.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
