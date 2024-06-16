package com.estudos.vendas.service;

import com.estudos.vendas.dto.PedidoDTO;
import com.estudos.vendas.entity.Pedido;

public interface PedidoServico {

    Pedido save(PedidoDTO pedidoDTO);

    Pedido getPedidoById(Integer id);

}
