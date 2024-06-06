package com.estudos.vendas.repository;

import com.estudos.vendas.domain.ItemPedido;
import com.estudos.vendas.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
