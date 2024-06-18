package com.estudos.vendas.service;

import com.estudos.vendas.dto.PedidoDTO;
import com.estudos.vendas.entity.Pedido;

import java.util.Optional;

public interface PedidoServico {

    Pedido save(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);

}
