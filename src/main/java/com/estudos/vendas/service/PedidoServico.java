package com.estudos.vendas.service;

import com.estudos.vendas.dto.entrada.AtualizaStatusPedidoDTO;
import com.estudos.vendas.dto.entrada.PedidoDTO;
import com.estudos.vendas.entity.Pedido;
import com.estudos.vendas.enums.StatusPedido;

import java.util.Optional;

public interface PedidoServico {

    Pedido save(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
